#include <DHT.h>
#include <WiFi.h>
#include <ArduinoJson.h>
#include <PubSubClient.h>

// === Configurações de rede ===
const char* ssid = "joao-connection";
const char* password = "joao1234";

// === Configurações do broker MQTT ===
const char* mqtt_server = "broker.emqx.io";
const int mqtt_port = 1883;
const char* mqtt_user = "iot-network-broker";                       
const char* mqtt_password = "iot-network-broker";            

// === ID do dispositivo ===
const char* DEVICE_ID = "device01";
const char* AIR_ID = "air01";

// === LED ===
const int LED_PIN = 2; 

// === Tópicos MQTT ===
String topic_temp = "/temperature/" + String(DEVICE_ID);
String topic_ac = "/air_conditioning/" + String(AIR_ID);

// === Configuração do DHT ===
#define DHTPIN 15
#define DHTTYPE DHT11
DHT dht(DHTPIN, DHTTYPE);

// === Clientes WiFi e MQTT ===
WiFiClient espClient;
PubSubClient client(espClient);

void setup_wifi() {
  Serial.print("Conectando ao Wi-Fi: ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("\nWi-Fi conectado. IP: ");
  Serial.println(WiFi.localIP());
}

// == Callback para mensagens recebidas ==
void callback(char* topic, byte* payload, unsigned int length) {
  // Converte o payload para String
  String msg;
  for (unsigned int i = 0; i < length; i++) {
    msg += (char)payload[i];
  }

  Serial.print("Mensagem recebida no tópico ");
  Serial.print(topic);
  Serial.print(": ");
  Serial.println(msg);

  // Parse JSON
  StaticJsonDocument<200> doc;
  DeserializationError error = deserializeJson(doc, msg);

  const char* action = doc["action"];
  const char* airConditioner = doc["idAirConditioner"];

  if (strcmp(airConditioner, AIR_ID) != 0) {
    Serial.println("Mensagem para outro dispositivo. Ignorada.");
    return;
  }

  if (strcmp(action, "INCREASE") == 0) {
      digitalWrite(LED_PIN, HIGH);
      Serial.println("Ação: INCREASE - LED ACESO.");
  } else if (strcmp(action, "DECREASE") == 0) {
      digitalWrite(LED_PIN, LOW);
      Serial.println("Ação: DECREASE - LED APAGADO.");
  } else if (strcmp(action, "KEEP") == 0) {
      Serial.println("Ação: KEEP - LED CONTINUA NO ESTADO ATUAL");
  } else {
      Serial.println("Ação desconhecida.");
  }
}

void reconnect() {
  while (!client.connected()) {
    Serial.print("Tentando conectar ao MQTT...");
    if (client.connect(DEVICE_ID)) {
      Serial.println("Conectado!");
      client.subscribe(topic_ac.c_str());
      Serial.print("Inscrito em: ");
      Serial.println(topic_ac);
    } else {
      Serial.print("Falhou, rc=");
      Serial.print(client.state());
      Serial.println(". Tentando novamente em 5 segundos...");
      delay(5000);
    }
  }
}

void setup() {
  Serial.begin(115200);
  dht.begin();

  pinMode(LED_PIN, OUTPUT);
  digitalWrite(LED_PIN, LOW);

  setup_wifi();
  client.setServer(mqtt_server, mqtt_port);
  client.setCallback(callback);
}

void loop() {
  if (!client.connected()) {
    reconnect();
  }
  client.loop();

  delay(10000);

  float temperatura = dht.readTemperature();
  if (isnan(temperatura)) {
    Serial.println("Erro ao ler a temperatura!");
    return;
  }

  // Convert sensor data to JSON
  String payload = "{";
  payload += "\"idDevice\": \"" + String(DEVICE_ID) + "\",";
  payload += "\"temperature\": " + String(temperatura, 2);
  payload += "}";

  client.publish(topic_temp.c_str(), payload.c_str());
  Serial.print("Temperatura publicada em ");
  Serial.print(topic_temp);
  Serial.print(": ");
  Serial.println(payload);
}

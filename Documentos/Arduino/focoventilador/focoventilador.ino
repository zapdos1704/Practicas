#include <DHT.h>
#include <Wire.h>
#include <LiquidCrystal_I2C.h>

#define DHTPIN 7
#define DHTTYPE DHT11

LiquidCrystal_I2C lcd(0x27, 16, 2); 

DHT dht(DHTPIN, DHTTYPE);

#define PIN_CALEFACTOR 2
#define PIN_EXTRACTOR 3
boolean ban= true;

#define PUNTO_CONTROL 30.0  // Temperatura objetivo (°C)

void setup() {
  Serial.begin(9600);
  dht.begin();

  lcd.init();
  lcd.backlight();

  pinMode(PIN_CALEFACTOR, OUTPUT);
  pinMode(PIN_EXTRACTOR, OUTPUT);

  digitalWrite(PIN_CALEFACTOR, LOW); // Inicialmente apagados
  digitalWrite(PIN_EXTRACTOR, LOW);
}

void loop() {
  float hum = dht.readHumidity();
  float temperatura = dht.readTemperature();

  if (isnan(temperatura)) {
    Serial.println("Error al leer del sensor DHT");
    return;
  }

  Serial.print("Temperatura actual: ");
  Serial.print(temperatura);
  Serial.println(" °C");
  
  if (temperatura < PUNTO_CONTROL && ban) {
    // Activa calefactor, apaga extractor
    digitalWrite(PIN_CALEFACTOR, HIGH);
    digitalWrite(PIN_EXTRACTOR, LOW);
    Serial.println("Calefactor ENCENDIDO - Extractor APAGADO");
  }
  else if(temperatura>26 && !ban){
    // Apaga calefactor, activa extractor
    digitalWrite(PIN_CALEFACTOR, LOW);
    digitalWrite(PIN_EXTRACTOR, HIGH);
    Serial.println("Calefactor APAGADO - Extractor ENCENDIDO");
  }
  if(temperatura>=30)
    ban=false;
  else if(temperatura<=26)
    ban=true;
    // Mostrar en LCD
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("Temperatura: ");
  lcd.print(temperatura);
  lcd.print(" C");

  lcd.setCursor(0, 1);
  lcd.print("Humedad: ");
  lcd.print(hum);
  lcd.print(" %");


  delay(2000);
}
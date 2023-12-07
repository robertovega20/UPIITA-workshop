char data = 0;           //Variable that store receive input
void setup()
{
    Serial.begin(9600);  
}
void loop()
{
    if(Serial.available() > 0)       /*check for serial data availability*/
    {
        data = Serial.read();        /*read data coming from Bluetooth device*/
        Serial.print(data);          /*print values on serial monitor*/
        Serial.print("\n");          /*print new line*/
    }                            
}

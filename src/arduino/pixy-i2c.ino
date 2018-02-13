// Copyright Jonah Snider 2018

#include <SPI.h>
#include <Pixy.h>
#include <Wire.h>

// This is the main Pixy object 
Pixy pixy;

void setup() {
	Serial.begin(9600);
	Serial.print("[SETUP] Starting...\n");
	pixy.init();

	// Open connection on I2C on port 4
	Wire.begin(12);
	// When the open I2C port receives something, run this function
	Wire.onReceive(receiveEvent);
}

void loop() { 
	static int i = 0;
	int j;
	uint16_t blocks;
	char buf[32]; 

	// Grab blocks
	blocks = pixy.getBlocks();

	// Send the block information if it matches the first signature
	if (blocks[i].signature == 1) {
		i++;

		// Do this (print) every 50 frames because printing every
		if (i%50==0) {
			sprintf(buf, "[PIXY]Detected %d:\n", blocks);
			Serial.print(buf);
			for (j=0; j<blocks; j++) {
				sprintf(buf, " block %d: ", j);
				Serial.print(buf); 
				pixy.blocks[j].print();
				// X coordinate is 0 to 319. Width is 1 to 320.
				Wire.send('x' + blocks[i].x + 'w' + blocks[i].width);
				Serial.print('[I2C] Just sent: x' + blocks[i].x + 'w' + blocks[i].width);
			}
		}
	}
}

void receiveEvent(int howMany) {
	// Set up an empty string of the data
	String data = "";

	// Data is sent one byte at a time, so piece it together
	while ( Wire.available() > 0 ) {
	char n=(char)Wire.read();
		if(((int)n)>((int)(' ')))
 		data += n; 
	}
	
	Serial.print('[I2C] Data received: %s\n', data);
}

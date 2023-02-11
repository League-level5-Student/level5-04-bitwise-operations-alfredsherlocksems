package _04_Light_Switches;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;

import game_tools.Game;
import game_tools.GameControlScene;

/*
 * Goal: Use binary operations to create a light show!
 * 
 * Implement the methods below using binary operations.
 * The 'lightsOnOff' variable is an 8-bit bitmap, meaning each bit is like an
 * on/off switch for one of the lights. A binary '1' indicates the light should
 * be on and a binary '0' indicates the light should be off. For example,
 *      bit   7    6    5     4      3     2    1    0
 *          cyan pink black yellow orange red green blue
 * where,
 *      lightsOnOff = 0b01010001    // blue, yellow, and pink are on
 *      lightsOnOff = 0x28          // orange and black are on
 *      lightsOnOff = 65            // green and cyan are on
 * 
 * The code is implemented correctly if the light pattern matches the pattern
 * in the LightSwitches.gif file in this folder.
 */
public class LightSwitches implements GameControlScene {
    static final int DISPLAY_WIDTH = 600;
    static final int DISPLAY_HEIGHT = 150;
    
    Deque<Runnable> workQueue;
    Game gameFrame;
    
    // Changing this array requires changing the code and instructions as well
    Color[] lightColors = {
             Color.BLUE,        // 0
             Color.GREEN,       // 1
             Color.RED,         // 2
             Color.ORANGE,      // 3
             Color.YELLOW,      // 4
             Color.BLACK,       // 5
             Color.PINK,        // 6
             Color.CYAN         // 7
            };

    // 8-bit bitmap. Leave as int so methods won't have to cast to a byte
    int lightsOnOff = 0b00000000;
    String s = "";

    /*
     * This method should check if the specified light is on, example:
     * index = 6        // return true if pink is on (bit 6 == 1)
     */
    boolean isLightOn(int index) {
    	printIntBinary(lightsOnOff);
    	String temp = "0b" + s;
    	if (temp.charAt(8 - index + 1) == '1' ) {
    		return true;
    	}
        return false;
    }
    
    /*
     * This method should only turn on 1 light, example:
     * index = 4        // turn off yellow only (set bit 4 = 1)
     */
    void turnLightOn(int index) {
    	printIntBinary(lightsOnOff);
        String temp = s;
        StringBuilder b  = new StringBuilder();
        b.append(temp);
        b.replace(8 - index,  8 - index, "1");
        BigInteger bigInteger = new BigInteger(b.toString(), 2);// 2 tells it that it's binary
        lightsOnOff = bigInteger.intValue(); //might have to fix replace to make it work with 
        //correct index location
        
    }
    
    /*
     * This method should only turn off 1 light
     * index = 0        // turn off blue only (set bit 0 = 0)
     */
    void turnLightOff(int index) {
    	 printIntBinary(lightsOnOff);
    	 String temp = s;
         StringBuilder b  = new StringBuilder();
         b.append(temp);
         b.replace(8 - index, 8 - index , "0");
         BigInteger bigInteger = new BigInteger(b.toString(), 2);// 2 tells it that it's binary
         lightsOnOff = bigInteger.intValue(); //might have to fix replace to make it work with 
         //correct index location
    }
    
    /*
     * This method should be able to turn on multiple lights
     * lightsBitmap = 0b01100110  // lights 1, 2, 5, 6 on
     */
    void turnMultiLightsOn(int lightsBitmap) {
    	printIntBinary(lightsBitmap);
    	String temp = "0b" + s;
        for (int i = 2; i < 10; i++) {
        	if (temp.charAt(i) == '1') {
        		turnLightOn(8 - i + 1);
        	}
        }
    }
    
    /*
     * This method should be able to turn off multiple lights
     * lightsBitmap = 0b10000001  // lights 0, 7 off
     */
    void turnMultiLightsOff(int lightsBitmap) {
    	printIntBinary(lightsBitmap);
    	String temp = "0b" + s;
        for (int i = 2; i < 10; i++) {
        	if (temp.charAt(i) == '1') {
        		turnLightOff(8+1 - i );
        	}
        }
    }
    
    /*
     * This method should toggle the state of multiple lights
     * example input:
     * lightsOnOff  = 0b10000001  // blue(0) and cyan(7) on
     * lightsBitmap = 0b10011001  // toggle lights 0, 3, 4, 7
     * output:
     * lightsOnOff  = 0b00011000  // blue(0) and cyan(7) off,
     *                               orange(3) and yellow(4) on
     */
    void toggleLights(int lightsBitmap) {
    	printIntBinary(lightsOnOff);
        String currentLights = "0b" + s;
        printIntBinary(lightsBitmap);
        String toggleLights = s;
        for (int i = 2; i < 10; i++) {
        	if (toggleLights.charAt(i) == '1') {
        		if (currentLights.charAt(i) == '0') {
        			turnLightOn(8 - i + 1);
        		}
        		else {
        			turnLightOff(8 - i + 1);
        		}
        	}
        }
    }
    
    public void printByteBinary(byte b) {
        // We first want to print the bit in the one's place
    	//System.out.println(b); // b == 1110 0001
    	byte a = b;
    	int p = 7;
    	s = "";
    	for (int i = 0; i < 8; i++) {
    		b = (byte) (b >> p); // 0000 0001
            // Use the & operator to "mask" the bit in the one's place
            // This can be done by "anding" (&) it with the value of 1
        	b = (byte) (b & 1); 
            // Print the result using System.out.print (NOT System.out.println)
        	s+=b;
            //Use this method to print the remaining 7 bits of b
        	b = a;
        	p--;
    	}
    }

    public void printShortBinary(short s) {
        // Create 2 byte variables
    	byte a = 0;
    	byte b = 0;
        // Use bit shifting and masking (&) to save the first
        // 8 bits of s in one byte, and the second 8 bits of
        // s in the other byte
    	short other = s;
    	System.out.println("s = " + s);
    	int p = 7;
    	byte actualA = 0;
    	for (int i = 0; i < 8; i++) {
    		a = (byte) (s >> p);
    		a = (byte) (a & 1);
    		actualA+=a;
    		s = other;
    		p--;
    	}
    	System.out.println("This is a: " + actualA);
    	a = (byte) (s << 8);
    	b = (byte) s;
        // Call printByteBinary twice using the two bytes
        // Make sure they are in the correct order
    	printByteBinary(a);
    	printByteBinary(b);
    }

    public void printIntBinary(int o) {
        // Create 2 short variables
    	short a = 0;
    	short b = 0;
    	int other = o;
    	System.out.println("Integer = " + o);
    	int p = 7;
    	short actualA = 0;
    	for (int i = 0; i < 16; i++) {
    		a = (short) (o >> p);
    		a = (short) (a & 1);
    		actualA+=a;
    		o = other;
    		p--;
    	}
    	System.out.println("This is a: " + actualA);
    	a = (short) (o << 8);
    	b = (short) o;
    	printShortBinary(a);
    	printShortBinary(b);
    	   // Use bit shifting and masking (&) to save the first
        // 16 bits of i in one short, and the second 16 bits of
        // i in the other short

        // Call printShortBinary twice using the two short variables
        // Make sure they are in the correct order
    }
    
    void runLightSequence1() {
        workQueue.add(()->turnMultiLightsOff(0xFF));
        workQueue.add(()->turnLightOn(0));
        workQueue.add(()->delayMs(200));
        
        for (int i = 0; i < lightColors.length - 1; i++) {
            final int iFinal = i;
            workQueue.add(()->turnLightOff(iFinal));
            workQueue.add(()->turnLightOn(iFinal + 1));
            workQueue.add(()->delayMs(200));
        }

        workQueue.add(()->turnLightOff(7));
    }
    
    void runLightSequence2(){
        workQueue.add(()->turnLightOn(7));
        workQueue.add(()->delayMs(200));
        
        for (int i = lightColors.length - 1; i > 0; i--) {
            final int iFinal = i;
            workQueue.add(()->turnLightOff(iFinal));
            workQueue.add(()->turnLightOn(iFinal - 1));
            workQueue.add(()->delayMs(200));
        }

        workQueue.add(()->turnLightOff(0));
    }
    
    void runLightSequence3(){
        workQueue.add(()->turnMultiLightsOff((1<<8) - 1));
        workQueue.add(()->delayMs(200));
        workQueue.add(()->turnMultiLightsOn(0b00011000));
        workQueue.add(()->delayMs(200));
        workQueue.add(()->turnMultiLightsOff(0b00011000));
        workQueue.add(()->turnMultiLightsOn(0b00100100));
        workQueue.add(()->delayMs(200));
        workQueue.add(()->turnMultiLightsOff(0b00100100));
        workQueue.add(()->turnMultiLightsOn(0b01000010));
        workQueue.add(()->delayMs(200));
        workQueue.add(()->turnMultiLightsOff(0b01000010));
        workQueue.add(()->turnMultiLightsOn(0b10000001));
        workQueue.add(()->delayMs(200));
        workQueue.add(()->turnMultiLightsOff((1<<8) - 1));
    }
    
    void runLightSequence4(){
        workQueue.add(()->turnMultiLightsOff((1<<8) - 1));
        workQueue.add(()->delayMs(500));
        workQueue.add(()->turnMultiLightsOn(0b10101010));
        workQueue.add(()->delayMs(500));
        workQueue.add(()->toggleLights((1<<8) - 1));
        workQueue.add(()->delayMs(500));
        workQueue.add(()->toggleLights(0b00001111));
        workQueue.add(()->delayMs(500));
        workQueue.add(()->toggleLights(0b11110000));
    }
    
    public LightSwitches() {
        gameFrame = new Game("Light Switches");
        gameFrame.setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        gameFrame.setScene(this);
        gameFrame.start();
        
        workQueue = new ArrayDeque<Runnable>();
        
        runLightSequence1();
        runLightSequence2();
        runLightSequence3();
        runLightSequence4();
    }

    @Override
    public void draw(Graphics g) {
        int x, y, width, height;
        
        if( workQueue != null && !workQueue.isEmpty()) {
            workQueue.pop().run();
        }
        
        for (int i = 0; i < lightColors.length; i++) {
            Graphics2D g2 = (Graphics2D) g;
            width = (DISPLAY_WIDTH - 20) / lightColors.length;
            height = width;
            x = i * width;
            y = 10;

            g2.setStroke(new BasicStroke(3));
            g2.setColor(lightColors[i]);
            
            if (isLightOn(i)) {
                g2.fillOval(x, y, width, height);
            } else {
                g2.drawOval(x, y, width, height);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        
        switch( keycode ) {
            case KeyEvent.VK_1:
                runLightSequence1();
                break;
            case KeyEvent.VK_2:
                runLightSequence2();
                break;
            case KeyEvent.VK_3:
                runLightSequence3();
                break;
            case KeyEvent.VK_4:
                runLightSequence4();
                break;
            default:
                // Do nothing on unrecognized key
                break;
        }
    }
    
    void delayMs(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

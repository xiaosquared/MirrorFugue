package warp;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Wrapper for CornerPinSurface
 */
public class MFSurface {
	CornerPinSurface s;
	PVector TL;
	PVector TR;
	PVector BL;
	PVector BR;
	
	public MFSurface(PApplet parent, int width, int height, int res,
			int xTL, int yTL, int xTR, int yTR, int xBL, int yBL, int xBR, int yBR) {
		s = new CornerPinSurface(parent, width, height, res);
		s.setControlPoints(xTL, yTL, xTR, yTR, xBL, yBL, xBR, yBR);
		
		setPVectors(xTL, yTL, xTR, yTR, xBL, yBL, xBR, yBR);
	}
	
	public void setControlPoints(int xTL, int yTL, int xTR, int yTR, int xBL, int yBL, int xBR, int yBR) {
		s.setControlPoints(xTL, yTL, xTR, yTR, xBL, yBL, xBR, yBR);
		setPVectors(xTL, yTL, xTR, yTR, xBL, yBL, xBR, yBR);
		
	}
	
	private void setPVectors(int xTL, int yTL, int xTR, int yTR, int xBL, int yBL, int xBR, int yBR) {
		TL = new PVector(xTL, yTL);
		TR = new PVector(xTR, yTR);
		BL = new PVector(xBL, yBL);
		BR = new PVector(xBR, yBR);
	}
	
	public CornerPinSurface getSurface() {
		return s;
	}
	
	private void resetSurface() {
		s.setControlPoints((int) TL.x, (int) TL.y, (int) TR.x, (int) TR.y, (int) BL.x, (int) BL.y, (int) BR.x, (int) BR.y);
	}
	
	public void setTLx(int xTL) {
		TL.x = xTL;
		System.out.println("TLx: " + TL.x);
		resetSurface();
	}
	
	public void setTLy(int yTL) {
		TL.y = yTL;
		System.out.println("TLy: " + TL.y);
		resetSurface();
	}
	
	public void setTRx(int xTR) {
		TR.x = xTR;
		System.out.println("TRx: " + TR.x);
		resetSurface();
	}
	
	public void setTRy(int yTR) {
		TR.y = yTR;
		System.out.println("TRy: " + TR.y);
		resetSurface();
	}
	
	public void setBLx(int xBL) {
		BL.x = xBL;
		System.out.println("BLx: " + BL.x);
		resetSurface();
	}
	
	public void setBLy (int yBL) {
		BL.y = yBL;
		System.out.println("BLy: " + BL.y);
		resetSurface();
	}

	public void setBRx(int xBR) {
		BR.x = xBR;
		System.out.println("BRx: " + BR.x);
		resetSurface();
	}
	
	public void setBRy(int yBR) {
		BR.y = yBR;
		System.out.println("BRy: " + BR.y);
		resetSurface();
	}
	
	public void nudgeTLx(int amount) {
		setTLx((int)TL.x + amount);
	}
	
	public void nudgeTLy(int amount) {
		setTLy((int)TL.y + amount);
	}
	
	public void nudgeTRx(int amount) {
		setTRx((int)TR.x + amount);
	}
	
	public void nudgeTRy(int amount) {
		setTRy((int)TR.y + amount);
	}
	
	public void nudgeBLx(int amount) {
		setBLx((int)BL.x + amount);
	}
	
	public void nudgeBLy(int amount) {
		setBLy((int)BL.y + amount);
	}
	
	public void nudgeBRx(int amount) {
		setBRx((int)BR.x + amount);
	}
	
	public void nudgeBRy(int amount) {
		setBRy((int)BR.y + amount);
	}
}

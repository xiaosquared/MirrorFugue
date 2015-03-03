package warp;

import processing.core.PApplet;

public class SurfaceManager {
	MFSurface[] surfaces;
	
	int selectedSurface = -1;
	int selectedPoint = -1;
	
	public SurfaceManager(PApplet parent) {
		surfaces = new MFSurface[3];
		MFSurface s0 = new MFSurface(parent, 430, 200, 5);
		MFSurface s1 = new MFSurface(parent, 411, 200, 5);
		MFSurface s2 = new MFSurface(parent, 411, 200, 5);
		surfaces[0] = s0;
		surfaces[1] = s1;
		surfaces[2] = s2;
		
		SurfaceXML.initSurfaces(surfaces);

	}
	
	public void save() {
		SurfaceXML.updateSurfaces(surfaces);
	}
	
	public CornerPinSurface getSurface(int index) {
		return surfaces[index].getSurface();
	}
	
	public MFSurface getMFSurface(int index) {
		return surfaces[index];
	}
	
	public boolean surfaceSelected() {
		return selectedSurface >= 0;
	}
	
	public MFSurface getSelectedMFSurface(int index) {
		return surfaces[selectedSurface];
	}
	
	public void nudgeSelectedPointX(int amount) {
		if (selectedPoint == 1)
			surfaces[selectedSurface].nudgeTLx(amount);
		else if (selectedPoint == 2)
			surfaces[selectedSurface].nudgeTRx(amount);
		else if (selectedPoint == 3)
			surfaces[selectedSurface].nudgeBLx(amount);
		else if (selectedPoint == 4)
			surfaces[selectedSurface].nudgeBRx(amount);
		
		System.out.println("Modified Surface " + selectedSurface);
		surfaces[selectedSurface].printAll();
	}
	
	public void nudgeSelectedPointY(int amount) {
		if (selectedPoint == 1)
			surfaces[selectedSurface].nudgeTLy(amount);
		else if (selectedPoint == 2)
			surfaces[selectedSurface].nudgeTRy(amount);
		else if (selectedPoint == 3)
			surfaces[selectedSurface].nudgeBLy(amount);
		else if (selectedPoint == 4)
			surfaces[selectedSurface].nudgeBRy(amount);
	}
	
	public void setSelection(int i) {
		if ((i == 1) || (i == 2) || (i == 7) || (i == 8))
			selectedSurface = 0;
		else if ((i == 3) || (i == 4) || (i == 9) || (i == 10))
			selectedSurface = 1;
		else 
			selectedSurface = 2;
		
		if ((i == 1) || (i == 3) || (i == 5))
			selectedPoint = 1;
		else if ((i == 2) || (i == 4) || (i == 6))
			selectedPoint = 2;
		else if ((i == 7) || (i == 9) || (i == 11))
			selectedPoint = 3;
		else 
			selectedPoint = 4;
	}
}

// XX office init surface
//MFSurface s0 = new MFSurface(parent, 600, 170, 5, 
//		89, 556,	//TL 
//		461, 556, 		//TR
//		-22, 788, 		//BL
//		443, 788);		//BR
//
//MFSurface s1 = new MFSurface(parent, 259, 170, 5, 
//		459, 556,	//TL
//		  616, 555,	//TR
//		  443, 788,	//BL
//		  646, 788);	//BR
//
//MFSurface s2 = new MFSurface(parent, 354, 170, 5,
//		616, 555,	//TL
//		   838, 555,	//TR
//		   646, 788,	//BL
//		   922, 788);	//BR


// some old surface coordinates

//private void setSurfaces(){
//surface_0.setControlPoints(89, 556,	//TL 
//			461, 556, 		//TR
//			-22, 788, 		//BL
//			443, 788); 		//BR
//surface_1.setControlPoints(459, 556,	//TL
//		  616, 555,	//TR
//		  443, 788,	//BL
//		  646, 788);	//BR
//surface_2.setControlPoints(616, 555,	//TL
//		   838, 555,	//TR
//		   646, 788,	//BL
//		   922, 788);	//BR
//}

//private void setSurfaces(){
//surface_0.setControlPoints(94, 556,	//TL 
//			460, 556, 		//TR
//			-20, 788, 		//BL
//			444, 788); 		//BR
//surface_1.setControlPoints(460, 556,	//TL
//		  615, 555,	//TR
//		  444, 788,	//BL
//		  644, 788);	//BR
//surface_2.setControlPoints(615, 555,	//TL
//		   834, 555,	//TR
//		   644, 788,	//BL
//		   919, 788);	//BR
//}

// for old demo of Ravel 2nd movement
//private void setSurfacesOld() {
//surface_0.setControlPoints(89, 556,	//TL 
//		  460, 556, 		//TR
//		  -27, 788, 		//BL
//		  443, 788); 		//BR  
//surface_1.setControlPoints(459, 556,	//TL
//		  618, 560,	//TR
//		  443, 788,	//BL
//		  643, 788);	//BR
//surface_2.setControlPoints(618, 560,	//TL
//		  840, 560,	//TR
//		  643, 788,	//BL
//		  925, 788);	//BR
//}
//
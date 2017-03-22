package org.jsstl.examples.ForestFireQEST;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

import org.jsstl.core.formula.Formula;
import org.jsstl.core.formula.Signal;
import org.jsstl.core.formula.SignalStatistics2;
import org.jsstl.core.formula.jSSTLScript;
import org.jsstl.core.monitor.SpatialQuantitativeSignal;
import org.jsstl.core.space.GraphModel;
import org.jsstl.core.space.Location;
import org.jsstl.io.FolderSignalReader;
import org.jsstl.io.TxtSpatialQuantSat;
import org.jsstl.io.TxtSpatialQuantSignal;
import org.jsstl.monitor.spatial.ThreeValues;
import org.jsstl.monitor.spatial.SpatialThreeValues;
import org.jsstl.monitor.spatial.SpatialThreeValuesTransducer;
import org.jsstl.monitor.threevalues.ThreeValuesAtomic;
import org.jsstl.xtext.formulas.ScriptLoader;
import org.jsstl.xtext.formulas.sSTLSpecification.Model;

import Model.GlobalManager;

public class ForestFireAnalysisQEST {

	public static void main(String[] args) throws Exception {
		
		int runs = 10;
		String propertyName = "GSafe";
		
	    //Run simulation (to get the spatial structure)
     	TotalFire.TotalFire2.main(null);
					
		// %%%%%%%%%%  GRAPH  %%%%%%%%% //		
		// Designing the grid

		int valueX = GlobalManager.getLocationManager().TwoDx;
		int valueY = GlobalManager.getLocationManager().TwoDy;
		
		GraphModel graph = GraphModel.createGrid(valueX, valueY, 1.0);
		// Computing of the distance matrix
		graph.dMcomputation();

	// %%%%%%%%% PROPERTY %%%%%%% //		
	// loading the formulas files
	ScriptLoader loader  = new ScriptLoader();
	jSSTLScript script = loader.load("data/spreadFireQEST2.sstl");
	// Loading the variables. That we have defined in the formulas files.
	
//	/// %%%%%%%  DATA import %%%%%%%%%%%%/////////
	//String [] var = script.getVariables();
	
/////////////  many RUNS  //////////

	double endT = TotalFire.TotalFire2.simulationTime;	
	double deltat = 0.1;
	//int steps = (int) (endT/deltat)+1;

			//TotalFire.TotalFire2.main(null);
			double [][][] trajInit = ModelFire2.SimulatorFire2.data;
			double [] timeToInsertInit = ModelFire2.SimulatorFire2.timeArray;			
//			double [] val000= new double[traj.length];
//			double [] val0time= new double[traj[0].length];
//			for(int i=0; i < traj.length; i++){
//				val000[i]=traj[i][0][1];
//			}
//			for(int i=0; i < traj[0].length; i++){
//				val0time[i]=traj[0][i][0];
//			}
//			System.out.println("loc "+Arrays.toString(val000));
//			System.out.println("trajloc0 "+Arrays.toString(val0time));
			
			Signal signalInit = new Signal(graph, timeToInsertInit, trajInit);
			String[] varInit = {"O", "EM", "NN", "SS", "OS", "ON", "B", "I", "SF", "ST", "P"};
			signalInit.setVariables(varInit);
			signalInit.transfomTimeStep(endT,deltat);
		    SpatialQuantitativeSignal qSignalInit = script.quantitativeCheck(new HashMap<>(), propertyName, graph, signalInit);
		    int steps = qSignalInit.getSteps();
			SignalStatistics2 statistic = new SignalStatistics2(graph.getNumberOfLocations(),steps);	
		    statistic.add(qSignalInit.quantTraj());
		    
	for ( int j=1 ; j<=runs ; j++) {
		TotalFire.TotalFire2.main(null);
			
		double [][][] traj = ModelFire2.SimulatorFire2.data;
		double [] timeToInsert = ModelFire2.SimulatorFire2.timeArray;		
		
//		double [] val000= new double[traj.length];
//		double [] val0time= new double[traj[0].length];
//		for(int i=0; i < traj.length; i++){
//			val000[i]=traj[i][0][1];
//		}
//		for(int i=0; i < traj[0].length; i++){
//			val0time[i]=traj[0][i][0];
//		}
//		System.out.println("loc "+Arrays.toString(val000));
//		System.out.println("trajloc0 "+Arrays.toString(val0time));
		
		Signal signal = new Signal(graph, timeToInsert, traj);
		String[] var = {"O", "EM", "NN", "SS", "OS", "ON", "B", "I", "SF", "ST", "P"};
		signal.setVariables(var);
		signal.transfomTimeStep(endT,deltat);
	    SpatialQuantitativeSignal qSignal = script.quantitativeCheck(new HashMap<>(), propertyName, graph, signal);
	    
		statistic.add(qSignal.quantTraj());	
	    
    }
	double [][] meanTraj = statistic.getAverageTraj();
	
	
//	double [][] sq = statistic.getSquareTraj();
//	System.out.println(Arrays.toString(sq[0]));
	
	double [][] sdTraj = statistic.getStandardDeviationTraj();

//	for (int i=0; i< graph.getNumberOfLocations(); i++){
//	System.out.println(graph.getLocation(i) + " -> " + meanTraj[i][0]);
//}
	
	for (int i=0; i< graph.getNumberOfLocations(); i++){
		System.out.print(meanTraj[i][0] + ", ");
	}
	System.out.println(" ");
	
	
	for (int i=0; i< graph.getNumberOfLocations(); i++){
		System.out.print(sdTraj[i][0] + ", ");
	}
	
	System.out.println(" ");
	

	HashMap<Integer,SpatialThreeValues> TimeTSLValues1 = new HashMap<>();
	
	for (int j=0; j < meanTraj[0].length; j++){
	SpatialThreeValues resultFireTSL1 = new SpatialThreeValues(graph);
	for (int i=0; i < meanTraj.length; i++){		
		double a = meanTraj[i][j] - sdTraj[i][j];
		double b = meanTraj[i][j] + sdTraj[i][j];
		double k = 0.8;
		String check = ">";
		ThreeValues value1 = ThreeValuesAtomic.checkIneq(a, b, k, check);
		resultFireTSL1.addLoc(graph.getLocation(i), value1);
		TimeTSLValues1.put(j, resultFireTSL1);
	}
	}
	
//	SpatialThreeValues resultSIR2 = new SpatialThreeValues(graph);
//	
//	for (int i=0; i < meanTraj.length; i++){
//		double a = meanTraj[i][0] - sdTraj[i][0];
//		double b = meanTraj[i][0] + sdTraj[i][0];
//		double k = 0.3;
//		String check = "<";
//		ThreeValues value = ThreeValuesAtomic.checkIneq(a, b, k, check);
//		resultSIR2.addLoc(graph.getLocation(i), value);
//	}
	
//	for (int i=0; i< graph.getNumberOfLocations(); i++){
//		System.out.println(graph.getLocation(i) + " -> " + resultSIR.spatialThreeValues.get(graph.getLocation(i)));
//	}
	
//	System.out.println(" ");
//	
//	for (int i=0; i< graph.getNumberOfLocations(); i++){
//		if (resultSIR.spatialThreeValues.get(graph.getLocation(i))==ThreeValues.FALSE){
//		System.out.print(0 + ", ");}else{
//			if(resultSIR.spatialThreeValues.get(graph.getLocation(i))==ThreeValues.TRUE){
//				System.out.print(2 + ", ");
//			}else{
//				System.out.print(1 + ", ");
//			}
//		}
//	}
	
	
//	for (int i=0; i< graph.getNumberOfLocations(); i++){
//		if (resultSIR2.spatialThreeValues.get(graph.getLocation(i))==ThreeValues.FALSE){
//		System.out.print(0 + ", ");}else{
//			if(resultSIR2.spatialThreeValues.get(graph.getLocation(i))==ThreeValues.TRUE){
//				System.out.print(2 + ", ");
//			}else{
//				System.out.print(1 + ", ");
//			}
//		}
//	}
//	
//	SpatialThreeValues formula1 = SpatialThreeValuesTransducer.somewhere(resultSIR, 1, 2);
	
//	for (int i=0; i < formula1.spatialThreeValues.size(); i++){
//		System.out.println(graph.getLocation(i) + " -> " + formula1.spatialThreeValues.get(graph.getLocation(i)));
//	}
	
//	System.out.println(" ");
//	
//	for (int i=0; i< graph.getNumberOfLocations(); i++){
//		if (formula1.spatialThreeValues.get(graph.getLocation(i))==ThreeValues.FALSE){
//		System.out.print(0 + ", ");}else{
//			if(formula1.spatialThreeValues.get(graph.getLocation(i))==ThreeValues.TRUE){
//				System.out.print(2 + ", ");
//			}else{
//				System.out.print(1 + ", ");
//			}
//		}
//	}
	
//	SpatialThreeValues formula2 = SpatialThreeValuesTransducer.surround(resultSIR, resultSIR2, 1, 3);
//
//	System.out.println(" ");
//	
//	for (int i=0; i< graph.getNumberOfLocations(); i++){
//		if (formula2.spatialThreeValues.get(graph.getLocation(i))==ThreeValues.FALSE){
//		System.out.print(0 + ", ");}else{
//			if(formula2.spatialThreeValues.get(graph.getLocation(i))==ThreeValues.TRUE){
//				System.out.print(2 + ", ");
//			}else{
//				System.out.print(1 + ", ");
//			}
//		}
//	}
	
	/////  write  (I commented this for the moment)
//		String text = "";
//		for (int i=0; i<meanTraj.length;i++) {
//			for (int j = 0; j < meanTraj[0].length; j++) {
//					text += String.format(Locale.US, " %20.10f", meanTraj[i][j]);
//			}
//			text += "\n";
//		}
//		PrintWriter printer = new PrintWriter("data/meanDataQuantSignalFireQEST2.txt");
//		printer.print(text);
//		printer.close();
	
	
	String text = "";	
	
		
		for (int i=0; i< graph.getNumberOfLocations(); i++){
			for (int j=0; j<TimeTSLValues1.size();j++) {
			if (TimeTSLValues1.get(j).spatialThreeValues.get(graph.getLocation(i))==ThreeValues.FALSE){
				text += String.format(Locale.US, " %20.10f", 0.2);}else{
					if(TimeTSLValues1.get(j).spatialThreeValues.get(graph.getLocation(i))==ThreeValues.TRUE){
						text += String.format(Locale.US, " %20.10f", 0.8);
					}else{
						text += String.format(Locale.US, " %20.10f", 0.5);
					}
				}			
		}
		text += "\n";
	}
	PrintWriter printer = new PrintWriter("data/Speed5.txt");
	printer.print(text);
	printer.close();
	}	
	
	
}

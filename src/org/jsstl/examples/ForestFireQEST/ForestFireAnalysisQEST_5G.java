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

public class ForestFireAnalysisQEST_5G {

	public static void main(String[] args) throws Exception {
		
		int runs = 30;
		//Run more runs for this, correct properties!
		String propertyName1 = "GSafe1";
		String propertyName2 = "GSafe2";
		String propertyName3 = "GSafe3";
		String propertyName4 = "GSafe4";
		String propertyName5 = "GSafe5";
		String propertyName6 = "GSafe6";
		String propertyName7 = "GSafe7";
		
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

			TotalFire.TotalFire2.main(null);
			double [][][] trajInit1 = ModelFire2.SimulatorFire2.data;
			double [] timeToInsertInit1 = ModelFire2.SimulatorFire2.timeArray;		
			
			double [][][] trajInit2 = ModelFire2.SimulatorFire2.data;
			double [] timeToInsertInit2 = ModelFire2.SimulatorFire2.timeArray;	
			
			double [][][] trajInit3 = ModelFire2.SimulatorFire2.data;
			double [] timeToInsertInit3 = ModelFire2.SimulatorFire2.timeArray;	
			
			double [][][] trajInit4 = ModelFire2.SimulatorFire2.data;
			double [] timeToInsertInit4 = ModelFire2.SimulatorFire2.timeArray;	
			
			double [][][] trajInit5 = ModelFire2.SimulatorFire2.data;
			double [] timeToInsertInit5 = ModelFire2.SimulatorFire2.timeArray;	
			
			double [][][] trajInit6 = ModelFire2.SimulatorFire2.data;
			double [] timeToInsertInit6 = ModelFire2.SimulatorFire2.timeArray;	
			
			double [][][] trajInit7 = ModelFire2.SimulatorFire2.data;
			double [] timeToInsertInit7 = ModelFire2.SimulatorFire2.timeArray;	
			
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
			
			Signal signalInit1 = new Signal(graph, timeToInsertInit1, trajInit1);
			String[] varInit1 = {"O", "EM", "NN", "SS", "OS", "ON", "B", "I", "SF"};
			signalInit1.setVariables(varInit1);
			signalInit1.transfomTimeStep(endT,deltat);
			
			Signal signalInit2 = new Signal(graph, timeToInsertInit2, trajInit2);
			String[] varInit2 = {"O", "EM", "NN", "SS", "OS", "ON", "B", "I", "SF"};
			signalInit2.setVariables(varInit2);
			signalInit2.transfomTimeStep(endT,deltat);
			
			Signal signalInit3 = new Signal(graph, timeToInsertInit3, trajInit3);
			String[] varInit3 = {"O", "EM", "NN", "SS", "OS", "ON", "B", "I", "SF"};
			signalInit3.setVariables(varInit3);
			signalInit3.transfomTimeStep(endT,deltat);
			
			Signal signalInit4 = new Signal(graph, timeToInsertInit4, trajInit4);
			String[] varInit4 = {"O", "EM", "NN", "SS", "OS", "ON", "B", "I", "SF"};
			signalInit4.setVariables(varInit4);
			signalInit4.transfomTimeStep(endT,deltat);
			
			Signal signalInit5 = new Signal(graph, timeToInsertInit5, trajInit5);
			String[] varInit5 = {"O", "EM", "NN", "SS", "OS", "ON", "B", "I", "SF"};
			signalInit5.setVariables(varInit5);
			signalInit5.transfomTimeStep(endT,deltat);
			
			Signal signalInit6 = new Signal(graph, timeToInsertInit6, trajInit6);
			String[] varInit6 = {"O", "EM", "NN", "SS", "OS", "ON", "B", "I", "SF"};
			signalInit6.setVariables(varInit6);
			signalInit6.transfomTimeStep(endT,deltat);
			
			Signal signalInit7 = new Signal(graph, timeToInsertInit7, trajInit7);
			String[] varInit7 = {"O", "EM", "NN", "SS", "OS", "ON", "B", "I", "SF"};
			signalInit7.setVariables(varInit7);
			signalInit7.transfomTimeStep(endT,deltat);
			
		    SpatialQuantitativeSignal qSignalInit1 = script.quantitativeCheck(new HashMap<>(), propertyName1, graph, signalInit1);
		    int steps1 = qSignalInit1.getSteps();
			SignalStatistics2 statistic1 = new SignalStatistics2(graph.getNumberOfLocations(),steps1);	
		    statistic1.add(qSignalInit1.quantTraj());
		    
		    SpatialQuantitativeSignal qSignalInit2 = script.quantitativeCheck(new HashMap<>(), propertyName2, graph, signalInit2);
		    int steps2 = qSignalInit2.getSteps();
			SignalStatistics2 statistic2 = new SignalStatistics2(graph.getNumberOfLocations(),steps2);	
		    statistic2.add(qSignalInit2.quantTraj());
		    
		    SpatialQuantitativeSignal qSignalInit3 = script.quantitativeCheck(new HashMap<>(), propertyName3, graph, signalInit3);
		    int steps3 = qSignalInit3.getSteps();
			SignalStatistics2 statistic3 = new SignalStatistics2(graph.getNumberOfLocations(),steps3);	
		    statistic3.add(qSignalInit3.quantTraj());
		    
		    SpatialQuantitativeSignal qSignalInit4 = script.quantitativeCheck(new HashMap<>(), propertyName4, graph, signalInit4);
		    int steps4 = qSignalInit4.getSteps();
			SignalStatistics2 statistic4 = new SignalStatistics2(graph.getNumberOfLocations(),steps4);	
		    statistic4.add(qSignalInit4.quantTraj());
		    
		    SpatialQuantitativeSignal qSignalInit5 = script.quantitativeCheck(new HashMap<>(), propertyName5, graph, signalInit5);
		    int steps5 = qSignalInit5.getSteps();
			SignalStatistics2 statistic5 = new SignalStatistics2(graph.getNumberOfLocations(),steps5);	
		    statistic5.add(qSignalInit5.quantTraj());
		    
		    SpatialQuantitativeSignal qSignalInit6 = script.quantitativeCheck(new HashMap<>(), propertyName6, graph, signalInit6);
		    int steps6 = qSignalInit6.getSteps();
			SignalStatistics2 statistic6 = new SignalStatistics2(graph.getNumberOfLocations(),steps6);	
		    statistic6.add(qSignalInit6.quantTraj());
		    
		    SpatialQuantitativeSignal qSignalInit7 = script.quantitativeCheck(new HashMap<>(), propertyName7, graph, signalInit7);
		    int steps7 = qSignalInit7.getSteps();
			SignalStatistics2 statistic7 = new SignalStatistics2(graph.getNumberOfLocations(),steps7);	
		    statistic7.add(qSignalInit7.quantTraj());
		    
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
		String[] var = {"O", "EM", "NN", "SS", "OS", "ON", "B", "I", "SF"};
		signal.setVariables(var);
		signal.transfomTimeStep(endT,deltat);
		
	    SpatialQuantitativeSignal qSignal1 = script.quantitativeCheck(new HashMap<>(), propertyName1, graph, signal);    
		statistic1.add(qSignal1.quantTraj());	
	    
	    SpatialQuantitativeSignal qSignal2 = script.quantitativeCheck(new HashMap<>(), propertyName2, graph, signal);    
		statistic2.add(qSignal2.quantTraj());	
		
	    SpatialQuantitativeSignal qSignal3 = script.quantitativeCheck(new HashMap<>(), propertyName3, graph, signal);    
		statistic3.add(qSignal3.quantTraj());	
		
	    SpatialQuantitativeSignal qSignal4 = script.quantitativeCheck(new HashMap<>(), propertyName4, graph, signal);    
		statistic4.add(qSignal4.quantTraj());	
		
	    SpatialQuantitativeSignal qSignal5 = script.quantitativeCheck(new HashMap<>(), propertyName5, graph, signal);    
		statistic5.add(qSignal5.quantTraj());
		
	    SpatialQuantitativeSignal qSignal6 = script.quantitativeCheck(new HashMap<>(), propertyName6, graph, signal);    
		statistic6.add(qSignal6.quantTraj());	
		
	    SpatialQuantitativeSignal qSignal7 = script.quantitativeCheck(new HashMap<>(), propertyName7, graph, signal);    
		statistic7.add(qSignal7.quantTraj());	
    }
	
	double [][] meanTraj1 = statistic1.getAverageTraj();
	double [][] meanTraj2 = statistic2.getAverageTraj();
	double [][] meanTraj3 = statistic3.getAverageTraj();
	double [][] meanTraj4 = statistic4.getAverageTraj();
	double [][] meanTraj5 = statistic5.getAverageTraj();
	double [][] meanTraj6 = statistic6.getAverageTraj();
	double [][] meanTraj7 = statistic7.getAverageTraj();
	
	
//	double [][] sq = statistic.getSquareTraj();
//	System.out.println(Arrays.toString(sq[0]));
	
	double [][] sdTraj1 = statistic1.getStandardDeviationTraj();
	double [][] sdTraj2 = statistic2.getStandardDeviationTraj();
	double [][] sdTraj3 = statistic3.getStandardDeviationTraj();
	double [][] sdTraj4 = statistic4.getStandardDeviationTraj();
	double [][] sdTraj5 = statistic5.getStandardDeviationTraj();
	double [][] sdTraj6 = statistic6.getStandardDeviationTraj();
	double [][] sdTraj7 = statistic7.getStandardDeviationTraj();

//	for (int i=0; i< graph.getNumberOfLocations(); i++){
//	System.out.println(graph.getLocation(i) + " -> " + meanTraj[i][0]);
//}
	
//	for (int i=0; i< graph.getNumberOfLocations(); i++){
//		System.out.print(meanTraj[i][0] + ", ");
//	}
//	System.out.println(" ");
//	
//	
//	for (int i=0; i< graph.getNumberOfLocations(); i++){
//		System.out.print(sdTraj[i][0] + ", ");
//	}
//	
//	System.out.println(" ");
	

	HashMap<Integer,SpatialThreeValues> TimeTSLValues1 = new HashMap<>();
	for (int j=0; j < 10; j++){
	SpatialThreeValues resultFireTSL1 = new SpatialThreeValues(graph);
	for (int i=0; i < meanTraj1.length; i++){		
		double a = meanTraj1[i][j] - sdTraj1[i][j];
		double b = meanTraj1[i][j] + sdTraj1[i][j];
		double k = 0.8;
		String check = ">";
		ThreeValues value1 = ThreeValuesAtomic.checkIneq(a, b, k, check);
		resultFireTSL1.addLoc(graph.getLocation(i), value1);
		TimeTSLValues1.put(j, resultFireTSL1);
	}
	}

	HashMap<Integer,SpatialThreeValues> TimeTSLValues2 = new HashMap<>();
	for (int j=0; j < 10; j++){
	SpatialThreeValues resultFireTSL2 = new SpatialThreeValues(graph);
	for (int i=0; i < meanTraj2.length; i++){		
		double a = meanTraj2[i][j] - sdTraj2[i][j];
		double b = meanTraj2[i][j] + sdTraj2[i][j];
		double k = 0.8;
		String check = ">";
		ThreeValues value2 = ThreeValuesAtomic.checkIneq(a, b, k, check);
		resultFireTSL2.addLoc(graph.getLocation(i), value2);
		TimeTSLValues2.put(j, resultFireTSL2);
	}
	}
	
	HashMap<Integer,SpatialThreeValues> TimeTSLValues3 = new HashMap<>();	
	for (int j=0; j < 10; j++){
	SpatialThreeValues resultFireTSL3 = new SpatialThreeValues(graph);
	for (int i=0; i < meanTraj3.length; i++){		
		double a = meanTraj3[i][j] - sdTraj3[i][j];
		double b = meanTraj3[i][j] + sdTraj3[i][j];
		double k = 0.8;
		String check = ">";
		ThreeValues value3 = ThreeValuesAtomic.checkIneq(a, b, k, check);
		resultFireTSL3.addLoc(graph.getLocation(i), value3);
		TimeTSLValues3.put(j, resultFireTSL3);
	}
	}
	
	HashMap<Integer,SpatialThreeValues> TimeTSLValues4 = new HashMap<>();
	for (int j=0; j < 10; j++){
	SpatialThreeValues resultFireTSL4 = new SpatialThreeValues(graph);
	for (int i=0; i < meanTraj4.length; i++){		
		double a = meanTraj4[i][j] - sdTraj4[i][j];
		double b = meanTraj4[i][j] + sdTraj4[i][j];
		double k = 0.8;
		String check = ">";
		ThreeValues value4 = ThreeValuesAtomic.checkIneq(a, b, k, check);
		resultFireTSL4.addLoc(graph.getLocation(i), value4);
		TimeTSLValues4.put(j, resultFireTSL4);
	}
	}
	
	HashMap<Integer,SpatialThreeValues> TimeTSLValues5 = new HashMap<>();	
	for (int j=0; j < 10; j++){
	SpatialThreeValues resultFireTSL5 = new SpatialThreeValues(graph);
	for (int i=0; i < meanTraj5.length; i++){		
		double a = meanTraj5[i][j] - sdTraj5[i][j];
		double b = meanTraj5[i][j] + sdTraj5[i][j];
		double k = 0.8;
		String check = ">";
		ThreeValues value5 = ThreeValuesAtomic.checkIneq(a, b, k, check);
		resultFireTSL5.addLoc(graph.getLocation(i), value5);
		TimeTSLValues5.put(j, resultFireTSL5);
	}
	}
	
	HashMap<Integer,SpatialThreeValues> TimeTSLValues6 = new HashMap<>();	
	for (int j=0; j < 10; j++){
	SpatialThreeValues resultFireTSL6 = new SpatialThreeValues(graph);
	for (int i=0; i < meanTraj6.length; i++){		
		double a = meanTraj6[i][j] - sdTraj6[i][j];
		double b = meanTraj6[i][j] + sdTraj6[i][j];
		double k = 0.8;
		String check = ">";
		ThreeValues value6 = ThreeValuesAtomic.checkIneq(a, b, k, check);
		resultFireTSL6.addLoc(graph.getLocation(i), value6);
		TimeTSLValues6.put(j, resultFireTSL6);
	}
	}
	
	HashMap<Integer,SpatialThreeValues> TimeTSLValues7 = new HashMap<>();	
	for (int j=0; j < 10; j++){
	SpatialThreeValues resultFireTSL7 = new SpatialThreeValues(graph);
	for (int i=0; i < meanTraj7.length; i++){		
		double a = meanTraj7[i][j] - sdTraj7[i][j];
		double b = meanTraj7[i][j] + sdTraj7[i][j];
		double k = 0.8;
		String check = ">";
		ThreeValues value7 = ThreeValuesAtomic.checkIneq(a, b, k, check);
		resultFireTSL7.addLoc(graph.getLocation(i), value7);
		TimeTSLValues7.put(j, resultFireTSL7);
	}
	}

		
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
	PrintWriter printer = new PrintWriter("data/Globally1.txt");
	printer.print(text);
	printer.close();
		
	
	String text2 = "";		
	for (int i=0; i< graph.getNumberOfLocations(); i++){
		for (int j=0; j<TimeTSLValues2.size();j++) {
		if (TimeTSLValues2.get(j).spatialThreeValues.get(graph.getLocation(i))==ThreeValues.FALSE){
			text2 += String.format(Locale.US, " %20.10f", 0.2);}else{
				if(TimeTSLValues2.get(j).spatialThreeValues.get(graph.getLocation(i))==ThreeValues.TRUE){
					text2 += String.format(Locale.US, " %20.10f", 0.8);
				}else{
					text2 += String.format(Locale.US, " %20.10f", 0.5);
				}
			}			
	}
	text2 += "\n";
}
	
PrintWriter printer2 = new PrintWriter("data/Globally2.txt");
printer2.print(text2);
printer2.close();

String text3 = "";		
for (int i=0; i< graph.getNumberOfLocations(); i++){
	for (int j=0; j<TimeTSLValues3.size();j++) {
	if (TimeTSLValues3.get(j).spatialThreeValues.get(graph.getLocation(i))==ThreeValues.FALSE){
		text3 += String.format(Locale.US, " %20.10f", 0.2);}else{
			if(TimeTSLValues3.get(j).spatialThreeValues.get(graph.getLocation(i))==ThreeValues.TRUE){
				text3 += String.format(Locale.US, " %20.10f", 0.8);
			}else{
				text3 += String.format(Locale.US, " %20.10f", 0.5);
			}
		}			
}
text3 += "\n";
}
PrintWriter printer3 = new PrintWriter("data/Globally3.txt");
printer3.print(text3);
printer3.close();
	

String text4 = "";		
for (int i=0; i< graph.getNumberOfLocations(); i++){
	for (int j=0; j<TimeTSLValues4.size();j++) {
	if (TimeTSLValues4.get(j).spatialThreeValues.get(graph.getLocation(i))==ThreeValues.FALSE){
		text4 += String.format(Locale.US, " %20.10f", 0.2);}else{
			if(TimeTSLValues4.get(j).spatialThreeValues.get(graph.getLocation(i))==ThreeValues.TRUE){
				text4 += String.format(Locale.US, " %20.10f", 0.8);
			}else{
				text4 += String.format(Locale.US, " %20.10f", 0.5);
			}
		}			
}
text4 += "\n";
}
PrintWriter printer4 = new PrintWriter("data/Globally4.txt");
printer4.print(text4);
printer4.close();


String text5 = "";		
for (int i=0; i< graph.getNumberOfLocations(); i++){
	for (int j=0; j<TimeTSLValues5.size();j++) {
	if (TimeTSLValues5.get(j).spatialThreeValues.get(graph.getLocation(i))==ThreeValues.FALSE){
		text5 += String.format(Locale.US, " %20.10f", 0.2);}else{
			if(TimeTSLValues5.get(j).spatialThreeValues.get(graph.getLocation(i))==ThreeValues.TRUE){
				text5 += String.format(Locale.US, " %20.10f", 0.8);
			}else{
				text5 += String.format(Locale.US, " %20.10f", 0.5);
			}
		}			
}
text5 += "\n";
}
PrintWriter printer5 = new PrintWriter("data/Globally5.txt");
printer5.print(text5);
printer5.close();

String text6 = "";		
for (int i=0; i< graph.getNumberOfLocations(); i++){
	for (int j=0; j<TimeTSLValues6.size();j++) {
	if (TimeTSLValues6.get(j).spatialThreeValues.get(graph.getLocation(i))==ThreeValues.FALSE){
		text6 += String.format(Locale.US, " %20.10f", 0.2);}else{
			if(TimeTSLValues6.get(j).spatialThreeValues.get(graph.getLocation(i))==ThreeValues.TRUE){
				text6 += String.format(Locale.US, " %20.10f", 0.8);
			}else{
				text6 += String.format(Locale.US, " %20.10f", 0.5);
			}
		}			
}
text6 += "\n";
}
PrintWriter printer6 = new PrintWriter("data/Globally6.txt");
printer6.print(text6);
printer6.close();	
	
	String text7 = "";		
	for (int i=0; i< graph.getNumberOfLocations(); i++){
		for (int j=0; j<TimeTSLValues7.size();j++) {
		if (TimeTSLValues7.get(j).spatialThreeValues.get(graph.getLocation(i))==ThreeValues.FALSE){
			text7 += String.format(Locale.US, " %20.10f", 0.2);}else{
				if(TimeTSLValues7.get(j).spatialThreeValues.get(graph.getLocation(i))==ThreeValues.TRUE){
					text7 += String.format(Locale.US, " %20.10f", 0.8);
				}else{
					text7 += String.format(Locale.US, " %20.10f", 0.5);
				}
			}			
	}
	text7 += "\n";
	}
	PrintWriter printer7 = new PrintWriter("data/Globally7.txt");
	printer7.print(text7);
	printer7.close();

	}	
}

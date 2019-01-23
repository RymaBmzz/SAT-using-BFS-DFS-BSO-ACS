package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
public class MainController implements Initializable {

	@FXML
	private Label myMsg;
	public void generateRandom(ActionEvent event)
	{
		Random rand = new Random();
		int myrand = rand.nextInt(50) +1 ;
		myMsg.setText(Integer.toString(myrand));
		System.out.println(Integer.toString(myrand));
		
	}
	
	@FXML
    private Button btn1;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private BarChart<?, ?> BarChartSat;
    
    @FXML
    private TextArea timearea;
    

    @FXML
    private Button timebutton;
   
    @FXML
    private Label filelabel;
    
  
    @FXML
    private ComboBox<String> instanceUF;
	ObservableList<String> instance = FXCollections.observableArrayList("uf75-01.cnf","uf75-02.cnf","uf75-03.cnf","uf75-04.cnf","uf75-05.cnf","uf75-06.cnf","uf75-07.cnf","uf75-08.cnf","uf75-09.cnf","uf75-010.cnf",
			"uf75-011.cnf","uf75-012.cnf","uf75-013.cnf","uf75-014.cnf","uf75-015.cnf","uf75-016.cnf","uf75-017.cnf","uf75-018.cnf","uf75-019.cnf","uf75-020.cnf",
			"uf75-021.cnf","uf75-022.cnf","uf75-023.cnf","uf75-024.cnf","uf75-025.cnf","uf75-026.cnf","uf75-027.cnf","uf75-028.cnf","uf75-029.cnf","uf75-030.cnf",
			"uf75-031.cnf","uf75-032.cnf","uf75-033.cnf","uf75-034.cnf","uf75-035.cnf","uf75-036.cnf","uf75-037.cnf","uf75-038.cnf","uf75-039.cnf","uf75-040.cnf",
			"uf75-041.cnf","uf75-042.cnf","uf75-043.cnf","uf75-044.cnf","uf75-045.cnf","uf75-046.cnf","uf75-047.cnf","uf75-048.cnf","uf75-049.cnf","uf75-050.cnf",
			"uf75-051.cnf","uf75-052.cnf","uf75-053.cnf","uf75-054.cnf","uf75-055.cnf","uf75-056.cnf","uf75-057.cnf","uf75-058.cnf","uf75-059.cnf","uf75-060.cnf",
			"uf75-061.cnf","uf75-062.cnf","uf75-063.cnf","uf75-064.cnf","uf75-065.cnf","uf75-066.cnf","uf75-067.cnf","uf75-068.cnf","uf75-069.cnf","uf75-070.cnf",
			"uf75-071.cnf","uf75-072.cnf","uf75-073.cnf","uf75-074.cnf","uf75-075.cnf","uf75-076.cnf","uf75-077.cnf","uf75-078.cnf","uf75-079.cnf","uf75-080.cnf",
			"uf75-081.cnf","uf75-082.cnf","uf75-083.cnf","uf75-084.cnf","uf75-085.cnf","uf75-086.cnf","uf75-087.cnf","uf75-088.cnf","uf75-089.cnf","uf75-090.cnf",
			"uf75-091.cnf","uf75-092.cnf","uf75-093.cnf","uf75-094.cnf","uf75-095.cnf","uf75-096.cnf","uf75-097.cnf","uf75-098.cnf","uf75-099.cnf","uf75-0100.cnf"
	);

    @FXML
    private ComboBox<String> uuffile;

    @FXML
    private ComboBox<String> uffile;
	ObservableList<String> satfile = FXCollections.observableArrayList("instance 01","instance 02","instance 03","instance 04","instance 05","instance 06","instance 07","instance 08","instance 09","instance 010");

	  @FXML
	    private ComboBox<String> tauxmoy;

		ObservableList<String> tauxsat = FXCollections.observableArrayList("Largeur d'abord","Profondeur d'abord","Cout Uniforme","A* heuristique");
   
		@FXML
	    private LineChart<?, ?> linechart;
		
		  @FXML
		    private ComboBox<String> typefile;

			ObservableList<String> type = FXCollections.observableArrayList("instance uf","instance uuf");

			int typeinstance=0; // par défaut /0 veut dire instance uf , 1 instance uuf
		String nominstance=null;	
		int temps;	
		
		public void gettime()
		{
			String s=timearea.getText();
			if(s != null)
				temps= Integer.parseInt(s);
		}
		  public void set_type_file(ActionEvent event)
		  {
			  if(typefile.getValue().equals("instance uf"))
				  typeinstance=0;
			  
			  if(typefile.getValue().equals("instance uuf"))
				  typeinstance=0;
  
		  }
			
			
		 public void Linechart_tauxmoy(ActionEvent event)
			{
			 
			   XYChart.Series moydata= new XYChart.Series<>();
				moydata.setName("Taux moyen");
				if(typeinstance == 1) //fichier uuf
				{
				switch(tauxmoy.getValue())
				{
				case "Largeur d'abord":
				{
					 linechart.getData().clear();
					 linechart.setTitle("Taux moyen de clauses SAT atteint par BFS");
						moydata.getData().add(new XYChart.Data("1", 41.56));
						moydata.getData().add(new XYChart.Data("2", 41.5));
						moydata.getData().add(new XYChart.Data("3", 41.6));
						moydata.getData().add(new XYChart.Data("4", 40.98));
						moydata.getData().add(new XYChart.Data("5", 41.84));
						moydata.getData().add(new XYChart.Data("6", 41.81));
						moydata.getData().add(new XYChart.Data("7", 41.78));
						moydata.getData().add(new XYChart.Data("8", 41.69));
						moydata.getData().add(new XYChart.Data("9", 41.87));
						moydata.getData().add(new XYChart.Data("10", 41.78));
						linechart.getData().add(moydata);
					break;
				}
				
				case "Profondeur d'abord":
				{
					 linechart.getData().clear();
					 linechart.setTitle("Taux moyen de clauses SAT atteint par DFS");
						moydata.getData().add(new XYChart.Data("1", 90.95));
						moydata.getData().add(new XYChart.Data("2", 91.56));
						moydata.getData().add(new XYChart.Data("3", 91.32));
						moydata.getData().add(new XYChart.Data("4", 91.47));
						moydata.getData().add(new XYChart.Data("5", 91.87));
						moydata.getData().add(new XYChart.Data("6", 90.12));
						moydata.getData().add(new XYChart.Data("7", 90.95));
						moydata.getData().add(new XYChart.Data("8", 91.66));
						moydata.getData().add(new XYChart.Data("9", 92.43));
						moydata.getData().add(new XYChart.Data("10", 91.53));
						linechart.getData().add(moydata);
					break;
				}
				
				case "Cout Uniforme":
				{
					 linechart.getData().clear();
					 linechart.setTitle("Taux moyen de clauses SAT atteint par Cout Uniforme");
						moydata.getData().add(new XYChart.Data("1", 93.16));
						moydata.getData().add(new XYChart.Data("2", 93.07));
						moydata.getData().add(new XYChart.Data("3", 93.81));
						moydata.getData().add(new XYChart.Data("4", 92.67));
						moydata.getData().add(new XYChart.Data("5", 92.06));
						moydata.getData().add(new XYChart.Data("6", 91.96));
						moydata.getData().add(new XYChart.Data("7", 92.95));
						moydata.getData().add(new XYChart.Data("8", 92.67));
						moydata.getData().add(new XYChart.Data("9", 93.53));
						moydata.getData().add(new XYChart.Data("10", 91.44));
						linechart.getData().add(moydata);
					break;
				}
                
				case "A* heuristique":
				{
					 linechart.getData().clear();
					 linechart.setTitle("Taux moyen de clauses SAT atteint par A* heuristique");
						moydata.getData().add(new XYChart.Data("1", 94.64));
						moydata.getData().add(new XYChart.Data("2", 95.04));
						moydata.getData().add(new XYChart.Data("3", 95.1));
						moydata.getData().add(new XYChart.Data("4", 93.32));
						moydata.getData().add(new XYChart.Data("5", 93.29));
						moydata.getData().add(new XYChart.Data("6", 94.8));
						moydata.getData().add(new XYChart.Data("7", 95.2));
						moydata.getData().add(new XYChart.Data("8", 94.98));
						moydata.getData().add(new XYChart.Data("9", 95.84));
						moydata.getData().add(new XYChart.Data("10", 94.95));
						linechart.getData().add(moydata);
					break;
				}
				
			}
				}
				else  // data des instances uf 
					if(typeinstance == 0)
					{
						switch(tauxmoy.getValue())
						{
						case "Largeur d'abord":
						{
							 linechart.getData().clear();
							 linechart.setTitle("Taux moyen de clauses SAT atteint par BFS");
								moydata.getData().add(new XYChart.Data("1", 42.47));
								moydata.getData().add(new XYChart.Data("2", 49.11));
								moydata.getData().add(new XYChart.Data("3", 44.37));
								moydata.getData().add(new XYChart.Data("4", 42.60));
								moydata.getData().add(new XYChart.Data("5", 42.70));
								moydata.getData().add(new XYChart.Data("6", 42.87));
								moydata.getData().add(new XYChart.Data("7", 42.36));
								moydata.getData().add(new XYChart.Data("8", 42.70));
								moydata.getData().add(new XYChart.Data("9", 42.39));
								moydata.getData().add(new XYChart.Data("10", 42.97));
								linechart.getData().add(moydata);
							break;
						}
						
						case "Profondeur d'abord":
						{
							 linechart.getData().clear();
							 linechart.setTitle("Taux moyen de clauses SAT atteint par DFS");
								moydata.getData().add(new XYChart.Data("1", 90.22));
								moydata.getData().add(new XYChart.Data("2", 91.78));
								moydata.getData().add(new XYChart.Data("3", 89.85));
								moydata.getData().add(new XYChart.Data("4", 92.51));
								moydata.getData().add(new XYChart.Data("5", 91.66));
								moydata.getData().add(new XYChart.Data("6", 91.45));
								moydata.getData().add(new XYChart.Data("7", 91.28));
								moydata.getData().add(new XYChart.Data("8", 91.59));
								moydata.getData().add(new XYChart.Data("9", 91.76));
								moydata.getData().add(new XYChart.Data("10", 94.22));
								linechart.getData().add(moydata);
							break;
						}
						
						case "Cout Uniforme":
						{
							 linechart.getData().clear();
							 linechart.setTitle("Taux moyen de clauses SAT atteint par Cout Uniforme");
								moydata.getData().add(new XYChart.Data("1", 94.65));
								moydata.getData().add(new XYChart.Data("2", 96.58));
								moydata.getData().add(new XYChart.Data("3", 93.02));
								moydata.getData().add(new XYChart.Data("4", 91.38));
								moydata.getData().add(new XYChart.Data("5", 94.22));
								moydata.getData().add(new XYChart.Data("6", 95.08));
								moydata.getData().add(new XYChart.Data("7", 91.59));
								moydata.getData().add(new XYChart.Data("8", 92.65));
								moydata.getData().add(new XYChart.Data("9", 94.53));
								moydata.getData().add(new XYChart.Data("10", 93.88));
								linechart.getData().add(moydata);
							break;
						}
		                
						case "A* heuristique":
						{
							 linechart.getData().clear();
							 linechart.setTitle("Taux moyen de clauses SAT atteint par A* heuristique");
								moydata.getData().add(new XYChart.Data("1", 90.22));
								moydata.getData().add(new XYChart.Data("2", 96.46));
								moydata.getData().add(new XYChart.Data("3", 94.34));
								moydata.getData().add(new XYChart.Data("4", 94.53));
								moydata.getData().add(new XYChart.Data("5", 96.48));
								moydata.getData().add(new XYChart.Data("6", 95.56));
								moydata.getData().add(new XYChart.Data("7", 95.18));
								moydata.getData().add(new XYChart.Data("8", 95.01));
								moydata.getData().add(new XYChart.Data("9", 97.09));
								moydata.getData().add(new XYChart.Data("10", 96.03));
								linechart.getData().add(moydata);
							break;
						}
						
					}
						
						
						
					}
				
			}
		 
		 

    public void barchart_data_uf(ActionEvent event)
	{
	   XYChart.Series bfsdata= new XYChart.Series<>();
		bfsdata.setName("BFS");
		
		XYChart.Series dfsdata= new XYChart.Series<>();
		dfsdata.setName("DFS");
		
		XYChart.Series cudata= new XYChart.Series<>();
		cudata.setName("A* cout uniforme");
		
		XYChart.Series hdata= new XYChart.Series<>();
		hdata.setName("A* heuristique");
		
	   switch (uffile.getValue())
	   {
	   case "instance 01" ://if(uffile.getValue().equals("instance 01"))
	   {//  filelabel.setText("Résultat d'expérimentation de "+uffile.getValue());
		   BarChartSat.getData().clear();
		bfsdata.getData().add(new XYChart.Data("1", 132));
		bfsdata.getData().add(new XYChart.Data("2", 135));
		bfsdata.getData().add(new XYChart.Data("3", 144));
		bfsdata.getData().add(new XYChart.Data("4", 139));
		bfsdata.getData().add(new XYChart.Data("5", 135));
		bfsdata.getData().add(new XYChart.Data("6", 142));
		bfsdata.getData().add(new XYChart.Data("7", 140));
		bfsdata.getData().add(new XYChart.Data("8", 145));
		bfsdata.getData().add(new XYChart.Data("9", 139));
		bfsdata.getData().add(new XYChart.Data("10", 140));
		BarChartSat.getData().add(bfsdata);
		
		
		dfsdata.getData().add(new XYChart.Data("1", 289));
		dfsdata.getData().add(new XYChart.Data("2", 297));
		dfsdata.getData().add(new XYChart.Data("3", 290));
		dfsdata.getData().add(new XYChart.Data("4", 294));
		dfsdata.getData().add(new XYChart.Data("5", 291));
		dfsdata.getData().add(new XYChart.Data("6", 297));
		dfsdata.getData().add(new XYChart.Data("7", 288));
		dfsdata.getData().add(new XYChart.Data("8", 293));
		dfsdata.getData().add(new XYChart.Data("9", 294));
		dfsdata.getData().add(new XYChart.Data("10", 299));
		BarChartSat.getData().add(dfsdata);
		
		
		cudata.getData().add(new XYChart.Data("1", 303));
		cudata.getData().add(new XYChart.Data("2", 310));
		cudata.getData().add(new XYChart.Data("3", 307));
		cudata.getData().add(new XYChart.Data("4", 308));
		cudata.getData().add(new XYChart.Data("5", 304));
		cudata.getData().add(new XYChart.Data("6", 312));
		cudata.getData().add(new XYChart.Data("7", 305));
		cudata.getData().add(new XYChart.Data("8", 308));
		cudata.getData().add(new XYChart.Data("9", 310));
		cudata.getData().add(new XYChart.Data("10", 309));
		BarChartSat.getData().add(cudata);
		
		
		
		hdata.getData().add(new XYChart.Data("1", 312));
		hdata.getData().add(new XYChart.Data("2", 314));
		hdata.getData().add(new XYChart.Data("3", 312));
		hdata.getData().add(new XYChart.Data("4", 313));
		hdata.getData().add(new XYChart.Data("5", 311));
		hdata.getData().add(new XYChart.Data("6", 313));
		hdata.getData().add(new XYChart.Data("7", 310));
		hdata.getData().add(new XYChart.Data("8", 316));
		hdata.getData().add(new XYChart.Data("9", 311));
		hdata.getData().add(new XYChart.Data("10", 312));
		BarChartSat.getData().add(hdata);
		break;
	   }
	   
	   case "instance 02" :
	   {  
		   BarChartSat.getData().clear();
			bfsdata.getData().add(new XYChart.Data("1", 156));
			bfsdata.getData().add(new XYChart.Data("2", 164));
			bfsdata.getData().add(new XYChart.Data("3", 154));
			bfsdata.getData().add(new XYChart.Data("4", 159));
			bfsdata.getData().add(new XYChart.Data("5", 161));
			bfsdata.getData().add(new XYChart.Data("6", 157));
			bfsdata.getData().add(new XYChart.Data("7", 160));
			bfsdata.getData().add(new XYChart.Data("8", 158));
			bfsdata.getData().add(new XYChart.Data("9", 162));
			bfsdata.getData().add(new XYChart.Data("10", 165));
        	BarChartSat.getData().add(bfsdata);
			
			   //chart.getData().addAll(chart);
		       
		
			dfsdata.getData().add(new XYChart.Data("1", 299));
			dfsdata.getData().add(new XYChart.Data("2", 296));
			dfsdata.getData().add(new XYChart.Data("3", 294));
			dfsdata.getData().add(new XYChart.Data("4", 297));
			dfsdata.getData().add(new XYChart.Data("5", 299));
			dfsdata.getData().add(new XYChart.Data("6", 298));
			dfsdata.getData().add(new XYChart.Data("7", 297));
			dfsdata.getData().add(new XYChart.Data("8", 301));
			dfsdata.getData().add(new XYChart.Data("9", 300));
			dfsdata.getData().add(new XYChart.Data("10", 302));
			BarChartSat.getData().add(dfsdata);
			
			
			cudata.getData().add(new XYChart.Data("1", 312));
			cudata.getData().add(new XYChart.Data("2", 314));
			cudata.getData().add(new XYChart.Data("3", 309));
			cudata.getData().add(new XYChart.Data("4", 315));
			cudata.getData().add(new XYChart.Data("5", 315));
			cudata.getData().add(new XYChart.Data("6", 313));
			cudata.getData().add(new XYChart.Data("7", 316));
			cudata.getData().add(new XYChart.Data("8", 314));
			cudata.getData().add(new XYChart.Data("9", 315));
			cudata.getData().add(new XYChart.Data("10", 316));
			BarChartSat.getData().add(cudata);
			
			
			
			hdata.getData().add(new XYChart.Data("1", 313));
			hdata.getData().add(new XYChart.Data("2", 318));
			hdata.getData().add(new XYChart.Data("3", 311));
			hdata.getData().add(new XYChart.Data("4", 312));
			hdata.getData().add(new XYChart.Data("5", 312));
			hdata.getData().add(new XYChart.Data("6", 312));
			hdata.getData().add(new XYChart.Data("7", 311));
			hdata.getData().add(new XYChart.Data("8", 316));
			hdata.getData().add(new XYChart.Data("9", 314));
			hdata.getData().add(new XYChart.Data("10", 316));
			BarChartSat.getData().add(hdata);
			break;
	   }
	   case "instance 03" :
	   {
		   BarChartSat.getData().clear();
			bfsdata.getData().add(new XYChart.Data("1", 147));
			bfsdata.getData().add(new XYChart.Data("2", 152));
			bfsdata.getData().add(new XYChart.Data("3", 139));
			bfsdata.getData().add(new XYChart.Data("4", 149));
			bfsdata.getData().add(new XYChart.Data("5", 150));
			bfsdata.getData().add(new XYChart.Data("6", 145));
			bfsdata.getData().add(new XYChart.Data("7", 142));
			bfsdata.getData().add(new XYChart.Data("8", 140));
			bfsdata.getData().add(new XYChart.Data("9", 138));
			bfsdata.getData().add(new XYChart.Data("10", 140));
			BarChartSat.getData().add(bfsdata);
			
			
			dfsdata.getData().add(new XYChart.Data("1", 290));
			dfsdata.getData().add(new XYChart.Data("2", 293));
			dfsdata.getData().add(new XYChart.Data("3", 293));
			dfsdata.getData().add(new XYChart.Data("4", 292));
			dfsdata.getData().add(new XYChart.Data("5", 288));
			dfsdata.getData().add(new XYChart.Data("6", 296));
			dfsdata.getData().add(new XYChart.Data("7", 290));
			dfsdata.getData().add(new XYChart.Data("8", 285));
			dfsdata.getData().add(new XYChart.Data("9", 294));
			dfsdata.getData().add(new XYChart.Data("10", 299));
			BarChartSat.getData().add(dfsdata);
			
			
			cudata.getData().add(new XYChart.Data("1", 302));
			cudata.getData().add(new XYChart.Data("2", 301));
			cudata.getData().add(new XYChart.Data("3", 302));
			cudata.getData().add(new XYChart.Data("4", 303));
			cudata.getData().add(new XYChart.Data("5", 306));
			cudata.getData().add(new XYChart.Data("6", 303));
			cudata.getData().add(new XYChart.Data("7", 299));
			cudata.getData().add(new XYChart.Data("8", 303));
			cudata.getData().add(new XYChart.Data("9", 301));
			cudata.getData().add(new XYChart.Data("10", 303));
			BarChartSat.getData().add(cudata);
			
			
			
			hdata.getData().add(new XYChart.Data("1", 308));
			hdata.getData().add(new XYChart.Data("2", 308));
			hdata.getData().add(new XYChart.Data("3", 307));
			hdata.getData().add(new XYChart.Data("4", 305));
			hdata.getData().add(new XYChart.Data("5", 306));
			hdata.getData().add(new XYChart.Data("6", 304));
			hdata.getData().add(new XYChart.Data("7", 305));
			hdata.getData().add(new XYChart.Data("8", 306));
			hdata.getData().add(new XYChart.Data("9", 309));
			hdata.getData().add(new XYChart.Data("10", 308));
			BarChartSat.getData().add(hdata);
		   break;
	   }
	   
	   case "instance 04" :
	   {
		   BarChartSat.getData().clear();
			bfsdata.getData().add(new XYChart.Data("1", 132));
			bfsdata.getData().add(new XYChart.Data("2", 135));
			bfsdata.getData().add(new XYChart.Data("3", 144));
			bfsdata.getData().add(new XYChart.Data("4", 139));
			bfsdata.getData().add(new XYChart.Data("5", 135));
			bfsdata.getData().add(new XYChart.Data("6", 142));
			bfsdata.getData().add(new XYChart.Data("7", 140));
			bfsdata.getData().add(new XYChart.Data("8", 143));
			bfsdata.getData().add(new XYChart.Data("9", 139));
			bfsdata.getData().add(new XYChart.Data("10", 145));
			BarChartSat.getData().add(bfsdata);
			
			
			dfsdata.getData().add(new XYChart.Data("1", 301));
			dfsdata.getData().add(new XYChart.Data("2", 304));
			dfsdata.getData().add(new XYChart.Data("3", 299));
			dfsdata.getData().add(new XYChart.Data("4", 303));
			dfsdata.getData().add(new XYChart.Data("5", 305));
			dfsdata.getData().add(new XYChart.Data("6", 303));
			dfsdata.getData().add(new XYChart.Data("7", 298));
			dfsdata.getData().add(new XYChart.Data("8", 297));
			dfsdata.getData().add(new XYChart.Data("9", 299));
			dfsdata.getData().add(new XYChart.Data("10", 301));
			BarChartSat.getData().add(dfsdata);
			
			
			cudata.getData().add(new XYChart.Data("1", 297));
			cudata.getData().add(new XYChart.Data("2", 293));
			cudata.getData().add(new XYChart.Data("3", 301));
			cudata.getData().add(new XYChart.Data("4", 291));
			cudata.getData().add(new XYChart.Data("5", 299));
			cudata.getData().add(new XYChart.Data("6", 298));
			cudata.getData().add(new XYChart.Data("7", 300));
			cudata.getData().add(new XYChart.Data("8", 302));
			cudata.getData().add(new XYChart.Data("9", 301));
			cudata.getData().add(new XYChart.Data("10", 305));
			BarChartSat.getData().add(cudata);
			
			
			hdata.getData().add(new XYChart.Data("1", 310));
			hdata.getData().add(new XYChart.Data("2", 309));
			hdata.getData().add(new XYChart.Data("3", 305));
			hdata.getData().add(new XYChart.Data("4", 308));
			hdata.getData().add(new XYChart.Data("5", 304));
			hdata.getData().add(new XYChart.Data("6", 310));
			hdata.getData().add(new XYChart.Data("7", 309));
			hdata.getData().add(new XYChart.Data("8", 306));
			hdata.getData().add(new XYChart.Data("9", 305));
			hdata.getData().add(new XYChart.Data("10", 312));
			BarChartSat.getData().add(hdata);
		   break;
	   }
	   
	   case "instance 05" :
	   {
		   BarChartSat.getData().clear();
			bfsdata.getData().add(new XYChart.Data("1", 140));
			bfsdata.getData().add(new XYChart.Data("2", 141));
			bfsdata.getData().add(new XYChart.Data("3", 135));
			bfsdata.getData().add(new XYChart.Data("4", 144));
			bfsdata.getData().add(new XYChart.Data("5", 136));
			bfsdata.getData().add(new XYChart.Data("6", 139));
			bfsdata.getData().add(new XYChart.Data("7", 142));
			bfsdata.getData().add(new XYChart.Data("8", 137));
			bfsdata.getData().add(new XYChart.Data("9", 139));
			bfsdata.getData().add(new XYChart.Data("10", 135));
			BarChartSat.getData().add(bfsdata);
			
			
			dfsdata.getData().add(new XYChart.Data("1", 297));
			dfsdata.getData().add(new XYChart.Data("2", 293));
			dfsdata.getData().add(new XYChart.Data("3", 300));
			dfsdata.getData().add(new XYChart.Data("4", 303));
			dfsdata.getData().add(new XYChart.Data("5", 299));
			dfsdata.getData().add(new XYChart.Data("6", 296));
			dfsdata.getData().add(new XYChart.Data("7", 297));
			dfsdata.getData().add(new XYChart.Data("8", 288));
			dfsdata.getData().add(new XYChart.Data("9", 294));
			dfsdata.getData().add(new XYChart.Data("10", 302));
			BarChartSat.getData().add(dfsdata);
			
			
			cudata.getData().add(new XYChart.Data("1", 309));
			cudata.getData().add(new XYChart.Data("2", 309));
			cudata.getData().add(new XYChart.Data("3", 306));
			cudata.getData().add(new XYChart.Data("4", 306));
			cudata.getData().add(new XYChart.Data("5", 303));
			cudata.getData().add(new XYChart.Data("6", 305));
			cudata.getData().add(new XYChart.Data("7", 307));
			cudata.getData().add(new XYChart.Data("8", 308));
			cudata.getData().add(new XYChart.Data("9", 303));
			cudata.getData().add(new XYChart.Data("10", 308));
			BarChartSat.getData().add(cudata);
			
			
			
			hdata.getData().add(new XYChart.Data("1", 314));
			hdata.getData().add(new XYChart.Data("2", 312));
			hdata.getData().add(new XYChart.Data("3", 313));
			hdata.getData().add(new XYChart.Data("4", 314));
			hdata.getData().add(new XYChart.Data("5", 312));
			hdata.getData().add(new XYChart.Data("6", 313));
			hdata.getData().add(new XYChart.Data("7", 310));
			hdata.getData().add(new XYChart.Data("8", 314));
			hdata.getData().add(new XYChart.Data("9", 316));
			hdata.getData().add(new XYChart.Data("10", 314));
			BarChartSat.getData().add(hdata);
		   break;
	   }
	   
	   case "instance 06" :
	   {
		   BarChartSat.getData().clear();
			bfsdata.getData().add(new XYChart.Data("1", 143));
			bfsdata.getData().add(new XYChart.Data("2", 141));
			bfsdata.getData().add(new XYChart.Data("3", 132));
			bfsdata.getData().add(new XYChart.Data("4", 138));
			bfsdata.getData().add(new XYChart.Data("5", 140));
			bfsdata.getData().add(new XYChart.Data("6", 136));
			bfsdata.getData().add(new XYChart.Data("7", 140));
			bfsdata.getData().add(new XYChart.Data("8", 141));
			bfsdata.getData().add(new XYChart.Data("9", 144));
			bfsdata.getData().add(new XYChart.Data("10", 139));
			BarChartSat.getData().add(bfsdata);
			
			
			dfsdata.getData().add(new XYChart.Data("1", 293));
			dfsdata.getData().add(new XYChart.Data("2", 300));
			dfsdata.getData().add(new XYChart.Data("3", 298));
			dfsdata.getData().add(new XYChart.Data("4", 303));
			dfsdata.getData().add(new XYChart.Data("5", 294));
			dfsdata.getData().add(new XYChart.Data("6", 296));
			dfsdata.getData().add(new XYChart.Data("7", 294));
			dfsdata.getData().add(new XYChart.Data("8", 293));
			dfsdata.getData().add(new XYChart.Data("9", 297));
			dfsdata.getData().add(new XYChart.Data("10", 300));
			BarChartSat.getData().add(dfsdata);
			
			
			cudata.getData().add(new XYChart.Data("1", 309));
			cudata.getData().add(new XYChart.Data("2", 314));
			cudata.getData().add(new XYChart.Data("3", 309));
			cudata.getData().add(new XYChart.Data("4", 310));
			cudata.getData().add(new XYChart.Data("5", 308));
			cudata.getData().add(new XYChart.Data("6", 311));
			cudata.getData().add(new XYChart.Data("7", 303));
			cudata.getData().add(new XYChart.Data("8", 305));
			cudata.getData().add(new XYChart.Data("9", 310));
			cudata.getData().add(new XYChart.Data("10", 309));
			BarChartSat.getData().add(cudata);
			
			
		
			hdata.getData().add(new XYChart.Data("1", 312));
			hdata.getData().add(new XYChart.Data("2", 311));
			hdata.getData().add(new XYChart.Data("3", 311));
			hdata.getData().add(new XYChart.Data("4", 310));
			hdata.getData().add(new XYChart.Data("5", 312));
			hdata.getData().add(new XYChart.Data("6", 308));
			hdata.getData().add(new XYChart.Data("7", 316));
			hdata.getData().add(new XYChart.Data("8", 310));
			hdata.getData().add(new XYChart.Data("9", 311));
			hdata.getData().add(new XYChart.Data("10", 310));
			BarChartSat.getData().add(hdata);
		   break;
	   }
	   
	   case "instance 07" :
	   {
		   BarChartSat.getData().clear();
			bfsdata.getData().add(new XYChart.Data("1", 133));
			bfsdata.getData().add(new XYChart.Data("2", 140));
			bfsdata.getData().add(new XYChart.Data("3", 136));
			bfsdata.getData().add(new XYChart.Data("4", 139));
			bfsdata.getData().add(new XYChart.Data("5", 141));
			bfsdata.getData().add(new XYChart.Data("6", 135));
			bfsdata.getData().add(new XYChart.Data("7", 140));
			bfsdata.getData().add(new XYChart.Data("8", 132));
			bfsdata.getData().add(new XYChart.Data("9", 142));
			bfsdata.getData().add(new XYChart.Data("10", 141));
			BarChartSat.getData().add(bfsdata);
			
			
			dfsdata.getData().add(new XYChart.Data("1", 300));
			dfsdata.getData().add(new XYChart.Data("2", 297));
			dfsdata.getData().add(new XYChart.Data("3", 291));
			dfsdata.getData().add(new XYChart.Data("4", 297));
			dfsdata.getData().add(new XYChart.Data("5", 294));
			dfsdata.getData().add(new XYChart.Data("6", 300));
			dfsdata.getData().add(new XYChart.Data("7", 295));
			dfsdata.getData().add(new XYChart.Data("8", 295));
			dfsdata.getData().add(new XYChart.Data("9", 297));
			dfsdata.getData().add(new XYChart.Data("10", 299));
			BarChartSat.getData().add(dfsdata);
			
			
			cudata.getData().add(new XYChart.Data("1", 299));
			cudata.getData().add(new XYChart.Data("2", 296));
			cudata.getData().add(new XYChart.Data("3", 296));
			cudata.getData().add(new XYChart.Data("4", 297));
			cudata.getData().add(new XYChart.Data("5", 295));
			cudata.getData().add(new XYChart.Data("6", 295));
			cudata.getData().add(new XYChart.Data("7", 305));
			cudata.getData().add(new XYChart.Data("8", 302));
			cudata.getData().add(new XYChart.Data("9", 301));
			cudata.getData().add(new XYChart.Data("10", 298));
			BarChartSat.getData().add(cudata);
			
			
			hdata.getData().add(new XYChart.Data("1", 310));
			hdata.getData().add(new XYChart.Data("2", 311));
			hdata.getData().add(new XYChart.Data("3", 309));
			hdata.getData().add(new XYChart.Data("4", 307));
			hdata.getData().add(new XYChart.Data("5", 310));
			hdata.getData().add(new XYChart.Data("6", 309));
			hdata.getData().add(new XYChart.Data("7", 310));
			hdata.getData().add(new XYChart.Data("8", 309));
			hdata.getData().add(new XYChart.Data("9", 308));
			hdata.getData().add(new XYChart.Data("10", 311));
			BarChartSat.getData().add(hdata);
		   break;
	   }
	   
	   case "instance 08" :
	   {
		   BarChartSat.getData().clear();
			bfsdata.getData().add(new XYChart.Data("1", 137));
			bfsdata.getData().add(new XYChart.Data("2", 140));
			bfsdata.getData().add(new XYChart.Data("3", 139));
			bfsdata.getData().add(new XYChart.Data("4", 142));
			bfsdata.getData().add(new XYChart.Data("5", 138));
			bfsdata.getData().add(new XYChart.Data("6", 137));
			bfsdata.getData().add(new XYChart.Data("7", 140));
			bfsdata.getData().add(new XYChart.Data("8", 135));
			bfsdata.getData().add(new XYChart.Data("9", 140));
			bfsdata.getData().add(new XYChart.Data("10", 141));
			BarChartSat.getData().add(bfsdata);
			
			
			dfsdata.getData().add(new XYChart.Data("1", 298));
			dfsdata.getData().add(new XYChart.Data("2", 300));
			dfsdata.getData().add(new XYChart.Data("3", 294));
			dfsdata.getData().add(new XYChart.Data("4", 298));
			dfsdata.getData().add(new XYChart.Data("5", 299));
			dfsdata.getData().add(new XYChart.Data("6", 302));
			dfsdata.getData().add(new XYChart.Data("7", 298));
			dfsdata.getData().add(new XYChart.Data("8", 296));
			dfsdata.getData().add(new XYChart.Data("9", 299));
			dfsdata.getData().add(new XYChart.Data("10", 293));
			BarChartSat.getData().add(dfsdata);
			
			
			cudata.getData().add(new XYChart.Data("1", 302));
			cudata.getData().add(new XYChart.Data("2", 299));
			cudata.getData().add(new XYChart.Data("3", 304));
			cudata.getData().add(new XYChart.Data("4", 304));
			cudata.getData().add(new XYChart.Data("5", 299));
			cudata.getData().add(new XYChart.Data("6", 296));
			cudata.getData().add(new XYChart.Data("7", 300));
			cudata.getData().add(new XYChart.Data("8", 294));
			cudata.getData().add(new XYChart.Data("9", 307));
			cudata.getData().add(new XYChart.Data("10", 305));
			BarChartSat.getData().add(cudata);
			
			
			hdata.getData().add(new XYChart.Data("1", 312));
			hdata.getData().add(new XYChart.Data("2", 308));
			hdata.getData().add(new XYChart.Data("3", 310));
			hdata.getData().add(new XYChart.Data("4", 309));
			hdata.getData().add(new XYChart.Data("5", 307));
			hdata.getData().add(new XYChart.Data("6", 309));
			hdata.getData().add(new XYChart.Data("7", 310));
			hdata.getData().add(new XYChart.Data("8", 307));
			hdata.getData().add(new XYChart.Data("9", 308));
			hdata.getData().add(new XYChart.Data("10", 309));
			BarChartSat.getData().add(hdata);
		   break;
	   }
	   
	   case "instance 09" :
	   {
		   BarChartSat.getData().clear();
			bfsdata.getData().add(new XYChart.Data("1", 139));
			bfsdata.getData().add(new XYChart.Data("2", 142));
			bfsdata.getData().add(new XYChart.Data("3", 138));
			bfsdata.getData().add(new XYChart.Data("4", 136));
			bfsdata.getData().add(new XYChart.Data("5", 135));
			bfsdata.getData().add(new XYChart.Data("6", 132));
			bfsdata.getData().add(new XYChart.Data("7", 140));
			bfsdata.getData().add(new XYChart.Data("8", 141));
			bfsdata.getData().add(new XYChart.Data("9", 140));
			bfsdata.getData().add(new XYChart.Data("10", 137));
			BarChartSat.getData().add(bfsdata);
			
			
			dfsdata.getData().add(new XYChart.Data("1", 296));
			dfsdata.getData().add(new XYChart.Data("2", 299));
			dfsdata.getData().add(new XYChart.Data("3", 302));
			dfsdata.getData().add(new XYChart.Data("4", 302));
			dfsdata.getData().add(new XYChart.Data("5", 294));
			dfsdata.getData().add(new XYChart.Data("6", 296));
			dfsdata.getData().add(new XYChart.Data("7", 299));
			dfsdata.getData().add(new XYChart.Data("8", 299));
			dfsdata.getData().add(new XYChart.Data("9", 300));
			dfsdata.getData().add(new XYChart.Data("10", 296));
			BarChartSat.getData().add(dfsdata);
			
			
			cudata.getData().add(new XYChart.Data("1", 301));
			cudata.getData().add(new XYChart.Data("2", 306));
			cudata.getData().add(new XYChart.Data("3", 301));
			cudata.getData().add(new XYChart.Data("4", 307));
			cudata.getData().add(new XYChart.Data("5", 308));
			cudata.getData().add(new XYChart.Data("6", 309));
			cudata.getData().add(new XYChart.Data("7", 305));
			cudata.getData().add(new XYChart.Data("8", 308));
			cudata.getData().add(new XYChart.Data("9", 314));
			cudata.getData().add(new XYChart.Data("10", 311));
			BarChartSat.getData().add(cudata);
			
			
			
			hdata.getData().add(new XYChart.Data("1", 318));
			hdata.getData().add(new XYChart.Data("2", 317));
			hdata.getData().add(new XYChart.Data("3", 316));
			hdata.getData().add(new XYChart.Data("4", 314));
			hdata.getData().add(new XYChart.Data("5", 315));
			hdata.getData().add(new XYChart.Data("6", 315));
			hdata.getData().add(new XYChart.Data("7", 310));
			hdata.getData().add(new XYChart.Data("8", 314));
			hdata.getData().add(new XYChart.Data("9", 316));
			hdata.getData().add(new XYChart.Data("10", 315));
			BarChartSat.getData().add(hdata);
		   break;
	   }
	   
	   case "instance 010" :
	   {
		   BarChartSat.getData().clear();
			bfsdata.getData().add(new XYChart.Data("1", 141));
			bfsdata.getData().add(new XYChart.Data("2", 138));
			bfsdata.getData().add(new XYChart.Data("3", 140));
			bfsdata.getData().add(new XYChart.Data("4", 142));
			bfsdata.getData().add(new XYChart.Data("5", 139));
			bfsdata.getData().add(new XYChart.Data("6", 140));
			bfsdata.getData().add(new XYChart.Data("7", 142));
			bfsdata.getData().add(new XYChart.Data("8", 138));
			bfsdata.getData().add(new XYChart.Data("9", 139));
			bfsdata.getData().add(new XYChart.Data("10", 140));
			BarChartSat.getData().add(bfsdata);
			
			
			dfsdata.getData().add(new XYChart.Data("1", 310));
			dfsdata.getData().add(new XYChart.Data("2", 303));
			dfsdata.getData().add(new XYChart.Data("3", 305));
			dfsdata.getData().add(new XYChart.Data("4", 303));
			dfsdata.getData().add(new XYChart.Data("5", 310));
			dfsdata.getData().add(new XYChart.Data("6", 305));
			dfsdata.getData().add(new XYChart.Data("7", 303));
			dfsdata.getData().add(new XYChart.Data("8", 306));
			dfsdata.getData().add(new XYChart.Data("9", 310));
			dfsdata.getData().add(new XYChart.Data("10", 304));
			BarChartSat.getData().add(dfsdata);
			
			
			cudata.getData().add(new XYChart.Data("1", 303));
			cudata.getData().add(new XYChart.Data("2", 303));
			cudata.getData().add(new XYChart.Data("3", 310));
			cudata.getData().add(new XYChart.Data("4", 304));
			cudata.getData().add(new XYChart.Data("5", 309));
			cudata.getData().add(new XYChart.Data("6", 305));
			cudata.getData().add(new XYChart.Data("7", 308));
			cudata.getData().add(new XYChart.Data("8", 301));
			cudata.getData().add(new XYChart.Data("9", 302));
			cudata.getData().add(new XYChart.Data("10", 309));
			BarChartSat.getData().add(cudata);
			
			
		
			hdata.getData().add(new XYChart.Data("1", 309));
			hdata.getData().add(new XYChart.Data("2", 312));
			hdata.getData().add(new XYChart.Data("3", 312));
			hdata.getData().add(new XYChart.Data("4", 313));
			hdata.getData().add(new XYChart.Data("5", 314));
			hdata.getData().add(new XYChart.Data("6", 312));
			hdata.getData().add(new XYChart.Data("7", 311));
			hdata.getData().add(new XYChart.Data("8", 313));
			hdata.getData().add(new XYChart.Data("9", 311));
			hdata.getData().add(new XYChart.Data("10", 313));
			BarChartSat.getData().add(hdata);
		   break;
	   }
	   
	   default: 
		   filelabel.setText("Veuillez refaire votre choix !");
		   break;
	   }
	}
   
   public void barchart_data_uuf(ActionEvent event)
	{
	   
	   XYChart.Series bfsdata= new XYChart.Series<>();
			bfsdata.setName("BFS");
			
			XYChart.Series dfsdata= new XYChart.Series<>();
			dfsdata.setName("DFS");
			
			XYChart.Series cudata= new XYChart.Series<>();
			cudata.setName("A* cout uniforme");
			
			XYChart.Series hdata= new XYChart.Series<>();
			hdata.setName("A* heuristique");
			
		   switch (uuffile.getValue())
		   {
		   case "instance 01" ://if(uffile.getValue().equals("instance 01"))
		   {//  filelabel.setText("Résultat d'expérimentation de "+uffile.getValue());
			   BarChartSat.getData().clear();
			bfsdata.getData().add(new XYChart.Data("1", 134));
			bfsdata.getData().add(new XYChart.Data("2", 135));
			bfsdata.getData().add(new XYChart.Data("3", 139));
			bfsdata.getData().add(new XYChart.Data("4", 135));
			bfsdata.getData().add(new XYChart.Data("5", 130));
			bfsdata.getData().add(new XYChart.Data("6", 134));
			bfsdata.getData().add(new XYChart.Data("7", 136));
			bfsdata.getData().add(new XYChart.Data("8", 137));
			bfsdata.getData().add(new XYChart.Data("9", 134));
			bfsdata.getData().add(new XYChart.Data("10", 133));
			BarChartSat.getData().add(bfsdata);
			
			
			dfsdata.getData().add(new XYChart.Data("1", 291));
			dfsdata.getData().add(new XYChart.Data("2", 297));
			dfsdata.getData().add(new XYChart.Data("3", 296));
			dfsdata.getData().add(new XYChart.Data("4", 296));
			dfsdata.getData().add(new XYChart.Data("5", 297));
			dfsdata.getData().add(new XYChart.Data("6", 294));
			dfsdata.getData().add(new XYChart.Data("7", 297));
			dfsdata.getData().add(new XYChart.Data("8", 296));
			dfsdata.getData().add(new XYChart.Data("9", 297));
			dfsdata.getData().add(new XYChart.Data("10", 295));
			BarChartSat.getData().add(dfsdata);
			
			
			cudata.getData().add(new XYChart.Data("1", 305));
			cudata.getData().add(new XYChart.Data("2", 298));
			cudata.getData().add(new XYChart.Data("3", 302));
			cudata.getData().add(new XYChart.Data("4", 309));
			cudata.getData().add(new XYChart.Data("5", 306));
			cudata.getData().add(new XYChart.Data("6", 306));
			cudata.getData().add(new XYChart.Data("7", 302));
			cudata.getData().add(new XYChart.Data("8", 301));
			cudata.getData().add(new XYChart.Data("9", 299));
			cudata.getData().add(new XYChart.Data("10", 300));
			BarChartSat.getData().add(cudata);
			
			
			
			hdata.getData().add(new XYChart.Data("1", 306));
			hdata.getData().add(new XYChart.Data("2", 307));
			hdata.getData().add(new XYChart.Data("3", 308));
			hdata.getData().add(new XYChart.Data("4", 311));
			hdata.getData().add(new XYChart.Data("5", 308));
			hdata.getData().add(new XYChart.Data("6", 308));
			hdata.getData().add(new XYChart.Data("7", 306));
			hdata.getData().add(new XYChart.Data("8", 307));
			hdata.getData().add(new XYChart.Data("9", 307));
			hdata.getData().add(new XYChart.Data("10", 308));
			BarChartSat.getData().add(hdata);
			break;
		   }
		   
		   case "instance 02" :
		   {  
			   BarChartSat.getData().clear();
				bfsdata.getData().add(new XYChart.Data("1", 137));
				bfsdata.getData().add(new XYChart.Data("2", 133));
				bfsdata.getData().add(new XYChart.Data("3", 139));
				bfsdata.getData().add(new XYChart.Data("4", 133));
				bfsdata.getData().add(new XYChart.Data("5", 135));
				bfsdata.getData().add(new XYChart.Data("6", 135));
				bfsdata.getData().add(new XYChart.Data("7", 132));
				bfsdata.getData().add(new XYChart.Data("8", 137));
				bfsdata.getData().add(new XYChart.Data("9", 133));
				bfsdata.getData().add(new XYChart.Data("10", 135));
	        	BarChartSat.getData().add(bfsdata);
				
				   //chart.getData().addAll(chart);
			       
			
				dfsdata.getData().add(new XYChart.Data("1", 295));
				dfsdata.getData().add(new XYChart.Data("2", 298));
				dfsdata.getData().add(new XYChart.Data("3", 297));
				dfsdata.getData().add(new XYChart.Data("4", 293));
				dfsdata.getData().add(new XYChart.Data("5", 302));
				dfsdata.getData().add(new XYChart.Data("6", 298));
				dfsdata.getData().add(new XYChart.Data("7", 297));
				dfsdata.getData().add(new XYChart.Data("8", 300));
				dfsdata.getData().add(new XYChart.Data("9", 298));
				dfsdata.getData().add(new XYChart.Data("10", 298));
				BarChartSat.getData().add(dfsdata);
				
				
				cudata.getData().add(new XYChart.Data("1", 300));
				cudata.getData().add(new XYChart.Data("2", 304));
				cudata.getData().add(new XYChart.Data("3", 300));
				cudata.getData().add(new XYChart.Data("4", 305));
				cudata.getData().add(new XYChart.Data("5", 302));
				cudata.getData().add(new XYChart.Data("6", 303));
				cudata.getData().add(new XYChart.Data("7", 302));
				cudata.getData().add(new XYChart.Data("8", 304));
				cudata.getData().add(new XYChart.Data("9", 303));
				cudata.getData().add(new XYChart.Data("10", 302));
				BarChartSat.getData().add(cudata);
				
				
				
				hdata.getData().add(new XYChart.Data("1", 308));
				hdata.getData().add(new XYChart.Data("2", 310));
				hdata.getData().add(new XYChart.Data("3", 311));
				hdata.getData().add(new XYChart.Data("4", 309));
				hdata.getData().add(new XYChart.Data("5", 306));
				hdata.getData().add(new XYChart.Data("6", 309));
				hdata.getData().add(new XYChart.Data("7", 311));
				hdata.getData().add(new XYChart.Data("8", 308));
				hdata.getData().add(new XYChart.Data("9", 309));
				hdata.getData().add(new XYChart.Data("10", 308));
				BarChartSat.getData().add(hdata);
				break;
		   }
		   case "instance 03" :
		   {
			   BarChartSat.getData().clear();
				bfsdata.getData().add(new XYChart.Data("1", 140));
				bfsdata.getData().add(new XYChart.Data("2", 133));
				bfsdata.getData().add(new XYChart.Data("3", 139));
				bfsdata.getData().add(new XYChart.Data("4", 133));
				bfsdata.getData().add(new XYChart.Data("5", 135));
				bfsdata.getData().add(new XYChart.Data("6", 134));
				bfsdata.getData().add(new XYChart.Data("7", 133));
				bfsdata.getData().add(new XYChart.Data("8", 136));
				bfsdata.getData().add(new XYChart.Data("9", 135));
				bfsdata.getData().add(new XYChart.Data("10", 134));
				BarChartSat.getData().add(bfsdata);
				
				
				dfsdata.getData().add(new XYChart.Data("1", 296));
				dfsdata.getData().add(new XYChart.Data("2", 303));
				dfsdata.getData().add(new XYChart.Data("3", 297));
				dfsdata.getData().add(new XYChart.Data("4", 294));
				dfsdata.getData().add(new XYChart.Data("5", 298));
				dfsdata.getData().add(new XYChart.Data("6", 296));
				dfsdata.getData().add(new XYChart.Data("7", 294));
				dfsdata.getData().add(new XYChart.Data("8", 300));
				dfsdata.getData().add(new XYChart.Data("9", 296));
				dfsdata.getData().add(new XYChart.Data("10", 294));
				BarChartSat.getData().add(dfsdata);
				
				
				cudata.getData().add(new XYChart.Data("1", 305));
				cudata.getData().add(new XYChart.Data("2", 308));
				cudata.getData().add(new XYChart.Data("3", 306));
				cudata.getData().add(new XYChart.Data("4", 303));
				cudata.getData().add(new XYChart.Data("5", 305));
				cudata.getData().add(new XYChart.Data("6", 304));
				cudata.getData().add(new XYChart.Data("7", 305));
				cudata.getData().add(new XYChart.Data("8", 304));
				cudata.getData().add(new XYChart.Data("9", 301));
				cudata.getData().add(new XYChart.Data("10", 308));
				BarChartSat.getData().add(cudata);
				
				
				
				hdata.getData().add(new XYChart.Data("1", 310));
				hdata.getData().add(new XYChart.Data("2", 309));
				hdata.getData().add(new XYChart.Data("3", 313));
				hdata.getData().add(new XYChart.Data("4", 306));
				hdata.getData().add(new XYChart.Data("5", 312));
				hdata.getData().add(new XYChart.Data("6", 310));
				hdata.getData().add(new XYChart.Data("7", 312));
				hdata.getData().add(new XYChart.Data("8", 311));
				hdata.getData().add(new XYChart.Data("9", 312));
				hdata.getData().add(new XYChart.Data("10", 312));
				BarChartSat.getData().add(hdata);
			   break;
		   }
		   
		   case "instance 04" :
		   {
			   BarChartSat.getData().clear();
				bfsdata.getData().add(new XYChart.Data("1", 135));
				bfsdata.getData().add(new XYChart.Data("2", 137));
				bfsdata.getData().add(new XYChart.Data("3", 129));
				bfsdata.getData().add(new XYChart.Data("4", 130));
				bfsdata.getData().add(new XYChart.Data("5", 137));
				bfsdata.getData().add(new XYChart.Data("6", 134));
				bfsdata.getData().add(new XYChart.Data("7", 130));
				bfsdata.getData().add(new XYChart.Data("8", 134));
				bfsdata.getData().add(new XYChart.Data("9", 132));
				bfsdata.getData().add(new XYChart.Data("10", 134));
				BarChartSat.getData().add(bfsdata);
				
				
				dfsdata.getData().add(new XYChart.Data("1", 299));
				dfsdata.getData().add(new XYChart.Data("2", 295));
				dfsdata.getData().add(new XYChart.Data("3", 296));
				dfsdata.getData().add(new XYChart.Data("4", 295));
				dfsdata.getData().add(new XYChart.Data("5", 294));
				dfsdata.getData().add(new XYChart.Data("6", 296));
				dfsdata.getData().add(new XYChart.Data("7", 301));
				dfsdata.getData().add(new XYChart.Data("8", 298));
				dfsdata.getData().add(new XYChart.Data("9", 301));
				dfsdata.getData().add(new XYChart.Data("10",298));
				BarChartSat.getData().add(dfsdata);
				
				
				cudata.getData().add(new XYChart.Data("1", 300));
				cudata.getData().add(new XYChart.Data("2", 304));
				cudata.getData().add(new XYChart.Data("3", 302));
				cudata.getData().add(new XYChart.Data("4", 302));
				cudata.getData().add(new XYChart.Data("5", 304));
				cudata.getData().add(new XYChart.Data("6", 297));
				cudata.getData().add(new XYChart.Data("7", 299));
				cudata.getData().add(new XYChart.Data("8", 301));
				cudata.getData().add(new XYChart.Data("9", 303));
				cudata.getData().add(new XYChart.Data("10", 300));
				BarChartSat.getData().add(cudata);
				
				
				hdata.getData().add(new XYChart.Data("1", 301));
				hdata.getData().add(new XYChart.Data("2", 305));
				hdata.getData().add(new XYChart.Data("3", 296));
				hdata.getData().add(new XYChart.Data("4", 302));
				hdata.getData().add(new XYChart.Data("5", 307));
				hdata.getData().add(new XYChart.Data("6", 305));
				hdata.getData().add(new XYChart.Data("7", 304));
				hdata.getData().add(new XYChart.Data("8", 305));
				hdata.getData().add(new XYChart.Data("9", 306));
				hdata.getData().add(new XYChart.Data("10", 302));
				BarChartSat.getData().add(hdata);
			   break;
		   }
		   
		   case "instance 05" :
		   {
			   BarChartSat.getData().clear();
				bfsdata.getData().add(new XYChart.Data("1", 137));
				bfsdata.getData().add(new XYChart.Data("2", 141));
				bfsdata.getData().add(new XYChart.Data("3", 135));
				bfsdata.getData().add(new XYChart.Data("4", 133));
				bfsdata.getData().add(new XYChart.Data("5", 137));
				bfsdata.getData().add(new XYChart.Data("6", 136));
				bfsdata.getData().add(new XYChart.Data("7", 133));
				bfsdata.getData().add(new XYChart.Data("8", 137));
				bfsdata.getData().add(new XYChart.Data("9", 134));
				bfsdata.getData().add(new XYChart.Data("10", 137));
				BarChartSat.getData().add(bfsdata);
				
				
				dfsdata.getData().add(new XYChart.Data("1", 296));
				dfsdata.getData().add(new XYChart.Data("2", 296));
				dfsdata.getData().add(new XYChart.Data("3", 301));
				dfsdata.getData().add(new XYChart.Data("4", 300));
				dfsdata.getData().add(new XYChart.Data("5", 300));
				dfsdata.getData().add(new XYChart.Data("6", 296));
				dfsdata.getData().add(new XYChart.Data("7", 297));
				dfsdata.getData().add(new XYChart.Data("8", 298));
				dfsdata.getData().add(new XYChart.Data("9", 302));
				dfsdata.getData().add(new XYChart.Data("10", 300));
				BarChartSat.getData().add(dfsdata);
				
				
				cudata.getData().add(new XYChart.Data("1", 297));
				cudata.getData().add(new XYChart.Data("2", 297));
				cudata.getData().add(new XYChart.Data("3", 301));
				cudata.getData().add(new XYChart.Data("4", 303));
				cudata.getData().add(new XYChart.Data("5", 302));
				cudata.getData().add(new XYChart.Data("6", 300));
				cudata.getData().add(new XYChart.Data("7", 296));
				cudata.getData().add(new XYChart.Data("8", 300));
				cudata.getData().add(new XYChart.Data("9", 301));
				cudata.getData().add(new XYChart.Data("10", 295));
				BarChartSat.getData().add(cudata);
				
				
				
				hdata.getData().add(new XYChart.Data("1", 305));
				hdata.getData().add(new XYChart.Data("2", 300));
				hdata.getData().add(new XYChart.Data("3", 301));
				hdata.getData().add(new XYChart.Data("4", 303));
				hdata.getData().add(new XYChart.Data("5", 305));
				hdata.getData().add(new XYChart.Data("6", 303));
				hdata.getData().add(new XYChart.Data("7", 301));
				hdata.getData().add(new XYChart.Data("8", 300));
				hdata.getData().add(new XYChart.Data("9", 304));
				hdata.getData().add(new XYChart.Data("10", 310));
				BarChartSat.getData().add(hdata);
			   break;
		   }
		   
		   case "instance 06" :
		   {
			   BarChartSat.getData().clear();
				bfsdata.getData().add(new XYChart.Data("1", 133));
				bfsdata.getData().add(new XYChart.Data("2", 135));
				bfsdata.getData().add(new XYChart.Data("3", 136));
				bfsdata.getData().add(new XYChart.Data("4", 135));
				bfsdata.getData().add(new XYChart.Data("5", 134));
				bfsdata.getData().add(new XYChart.Data("6", 141));
				bfsdata.getData().add(new XYChart.Data("7", 134));
				bfsdata.getData().add(new XYChart.Data("8", 136));
				bfsdata.getData().add(new XYChart.Data("9", 135));
				bfsdata.getData().add(new XYChart.Data("10", 140));
				BarChartSat.getData().add(bfsdata);
				
				
				dfsdata.getData().add(new XYChart.Data("1", 296));
				dfsdata.getData().add(new XYChart.Data("2", 294));
				dfsdata.getData().add(new XYChart.Data("3", 289));
				dfsdata.getData().add(new XYChart.Data("4", 291));
				dfsdata.getData().add(new XYChart.Data("5", 290));
				dfsdata.getData().add(new XYChart.Data("6", 296));
				dfsdata.getData().add(new XYChart.Data("7", 290));
				dfsdata.getData().add(new XYChart.Data("8", 297));
				dfsdata.getData().add(new XYChart.Data("9", 296));
				dfsdata.getData().add(new XYChart.Data("10", 290));
				BarChartSat.getData().add(dfsdata);
				
				
				cudata.getData().add(new XYChart.Data("1", 295));
				cudata.getData().add(new XYChart.Data("2", 297));
				cudata.getData().add(new XYChart.Data("3", 300));
				cudata.getData().add(new XYChart.Data("4", 299));
				cudata.getData().add(new XYChart.Data("5", 297));
				cudata.getData().add(new XYChart.Data("6", 302));
				cudata.getData().add(new XYChart.Data("7", 299));
				cudata.getData().add(new XYChart.Data("8", 300));
				cudata.getData().add(new XYChart.Data("9", 302));
				cudata.getData().add(new XYChart.Data("10", 298));
				BarChartSat.getData().add(cudata);
				
				
			
				hdata.getData().add(new XYChart.Data("1", 310));
				hdata.getData().add(new XYChart.Data("2", 309));
				hdata.getData().add(new XYChart.Data("3", 308));
				hdata.getData().add(new XYChart.Data("4", 308));
				hdata.getData().add(new XYChart.Data("5", 307));
				hdata.getData().add(new XYChart.Data("6", 308));
				hdata.getData().add(new XYChart.Data("7", 311));
				hdata.getData().add(new XYChart.Data("8", 304));
				hdata.getData().add(new XYChart.Data("9", 306));
				hdata.getData().add(new XYChart.Data("10", 310));
				BarChartSat.getData().add(hdata);
			   break;
		   }
		   
		   case "instance 07" :
		   {
			   BarChartSat.getData().clear();
				bfsdata.getData().add(new XYChart.Data("1", 134));
				bfsdata.getData().add(new XYChart.Data("2", 136));
				bfsdata.getData().add(new XYChart.Data("3", 134));
				bfsdata.getData().add(new XYChart.Data("4", 135));
				bfsdata.getData().add(new XYChart.Data("5", 141));
				bfsdata.getData().add(new XYChart.Data("6", 136));
				bfsdata.getData().add(new XYChart.Data("7", 135));
				bfsdata.getData().add(new XYChart.Data("8", 134));
				bfsdata.getData().add(new XYChart.Data("9", 137));
				bfsdata.getData().add(new XYChart.Data("10", 136));
				BarChartSat.getData().add(bfsdata);
				
				
				dfsdata.getData().add(new XYChart.Data("1", 290));
				dfsdata.getData().add(new XYChart.Data("2", 287));
				dfsdata.getData().add(new XYChart.Data("3", 295));
				dfsdata.getData().add(new XYChart.Data("4", 294));
				dfsdata.getData().add(new XYChart.Data("5", 293));
				dfsdata.getData().add(new XYChart.Data("6", 301));
				dfsdata.getData().add(new XYChart.Data("7", 297));
				dfsdata.getData().add(new XYChart.Data("8", 300));
				dfsdata.getData().add(new XYChart.Data("9", 299));
				dfsdata.getData().add(new XYChart.Data("10", 300));
				BarChartSat.getData().add(dfsdata);
				
				
				cudata.getData().add(new XYChart.Data("1", 303));
				cudata.getData().add(new XYChart.Data("2", 301));
				cudata.getData().add(new XYChart.Data("3", 298));
				cudata.getData().add(new XYChart.Data("4", 305));
				cudata.getData().add(new XYChart.Data("5", 304));
				cudata.getData().add(new XYChart.Data("6", 306));
				cudata.getData().add(new XYChart.Data("7", 299));
				cudata.getData().add(new XYChart.Data("8", 301));
				cudata.getData().add(new XYChart.Data("9", 303));
				cudata.getData().add(new XYChart.Data("10", 301	));
				BarChartSat.getData().add(cudata);	
				
				
				hdata.getData().add(new XYChart.Data("1", 308));
				hdata.getData().add(new XYChart.Data("2", 310));
				hdata.getData().add(new XYChart.Data("3", 310));
				hdata.getData().add(new XYChart.Data("4", 311));
				hdata.getData().add(new XYChart.Data("5", 309));
				hdata.getData().add(new XYChart.Data("6", 310));
				hdata.getData().add(new XYChart.Data("7", 311));
				hdata.getData().add(new XYChart.Data("8", 309));
				hdata.getData().add(new XYChart.Data("9", 309));
				hdata.getData().add(new XYChart.Data("10", 307));
				BarChartSat.getData().add(hdata);
			   break;
		   }
		   
		   case "instance 08" :
		   {
			   BarChartSat.getData().clear();
				bfsdata.getData().add(new XYChart.Data("1", 135));
				bfsdata.getData().add(new XYChart.Data("2", 135));
				bfsdata.getData().add(new XYChart.Data("3", 134));
				bfsdata.getData().add(new XYChart.Data("4", 138));
				bfsdata.getData().add(new XYChart.Data("5", 135));
				bfsdata.getData().add(new XYChart.Data("6", 134));
				bfsdata.getData().add(new XYChart.Data("7", 133));
				bfsdata.getData().add(new XYChart.Data("8", 139));
				bfsdata.getData().add(new XYChart.Data("9", 138));
				bfsdata.getData().add(new XYChart.Data("10", 134));
				BarChartSat.getData().add(bfsdata);
				
				
				dfsdata.getData().add(new XYChart.Data("1", 298));
				dfsdata.getData().add(new XYChart.Data("2", 297));
				dfsdata.getData().add(new XYChart.Data("3", 298));
				dfsdata.getData().add(new XYChart.Data("4", 299));
				dfsdata.getData().add(new XYChart.Data("5", 298));
				dfsdata.getData().add(new XYChart.Data("6", 297));
				dfsdata.getData().add(new XYChart.Data("7", 299));
				dfsdata.getData().add(new XYChart.Data("8", 298));
				dfsdata.getData().add(new XYChart.Data("9", 298));
				dfsdata.getData().add(new XYChart.Data("10", 297));
				BarChartSat.getData().add(dfsdata);
				
				
				cudata.getData().add(new XYChart.Data("1", 298));
				cudata.getData().add(new XYChart.Data("2", 300));
				cudata.getData().add(new XYChart.Data("3", 305));
				cudata.getData().add(new XYChart.Data("4", 304));
				cudata.getData().add(new XYChart.Data("5", 300));
				cudata.getData().add(new XYChart.Data("6", 299));
				cudata.getData().add(new XYChart.Data("7", 301));
				cudata.getData().add(new XYChart.Data("8", 300));
				cudata.getData().add(new XYChart.Data("9", 301));
				cudata.getData().add(new XYChart.Data("10", 304));
				BarChartSat.getData().add(cudata);
				
				
				hdata.getData().add(new XYChart.Data("1", 308));
				hdata.getData().add(new XYChart.Data("2", 310));
				hdata.getData().add(new XYChart.Data("3", 309));
				hdata.getData().add(new XYChart.Data("4", 309));
				hdata.getData().add(new XYChart.Data("5", 312));
				hdata.getData().add(new XYChart.Data("6", 310));
				hdata.getData().add(new XYChart.Data("7", 308));
				hdata.getData().add(new XYChart.Data("8", 309));
				hdata.getData().add(new XYChart.Data("9", 307));
				hdata.getData().add(new XYChart.Data("10", 305));
				BarChartSat.getData().add(hdata);
			   break;
		   }
		   
		   case "instance 09" :
		   {
			   BarChartSat.getData().clear();
				bfsdata.getData().add(new XYChart.Data("1", 134));
				bfsdata.getData().add(new XYChart.Data("2", 136));
				bfsdata.getData().add(new XYChart.Data("3", 139));
				bfsdata.getData().add(new XYChart.Data("4", 135));
				bfsdata.getData().add(new XYChart.Data("5", 134));
				bfsdata.getData().add(new XYChart.Data("6", 138));
				bfsdata.getData().add(new XYChart.Data("7", 134));
				bfsdata.getData().add(new XYChart.Data("8", 136));
				bfsdata.getData().add(new XYChart.Data("9", 135));
				bfsdata.getData().add(new XYChart.Data("10", 140));
				BarChartSat.getData().add(bfsdata);
				
				
				dfsdata.getData().add(new XYChart.Data("1", 300));
				dfsdata.getData().add(new XYChart.Data("2", 301));
				dfsdata.getData().add(new XYChart.Data("3", 297));
				dfsdata.getData().add(new XYChart.Data("4", 298));
				dfsdata.getData().add(new XYChart.Data("5", 304));
				dfsdata.getData().add(new XYChart.Data("6", 301));
				dfsdata.getData().add(new XYChart.Data("7", 300));
				dfsdata.getData().add(new XYChart.Data("8", 302));
				dfsdata.getData().add(new XYChart.Data("9", 301));
				dfsdata.getData().add(new XYChart.Data("10", 300));
				BarChartSat.getData().add(dfsdata);
				
				
				cudata.getData().add(new XYChart.Data("1", 309));
				cudata.getData().add(new XYChart.Data("2", 305));
				cudata.getData().add(new XYChart.Data("3", 300));
				cudata.getData().add(new XYChart.Data("4", 305));
				cudata.getData().add(new XYChart.Data("5", 307));
				cudata.getData().add(new XYChart.Data("6", 306));
				cudata.getData().add(new XYChart.Data("7", 300));
				cudata.getData().add(new XYChart.Data("8", 303));
				cudata.getData().add(new XYChart.Data("9", 303));
				cudata.getData().add(new XYChart.Data("10", 302));
				BarChartSat.getData().add(cudata);
				
				
				
				hdata.getData().add(new XYChart.Data("1", 312));
				hdata.getData().add(new XYChart.Data("2", 311));
				hdata.getData().add(new XYChart.Data("3", 314));
				hdata.getData().add(new XYChart.Data("4", 311));
				hdata.getData().add(new XYChart.Data("5", 312));
				hdata.getData().add(new XYChart.Data("6", 311));
				hdata.getData().add(new XYChart.Data("7", 311));
				hdata.getData().add(new XYChart.Data("8", 311));
				hdata.getData().add(new XYChart.Data("9", 312));
				hdata.getData().add(new XYChart.Data("10", 310));
				BarChartSat.getData().add(hdata);
			   break;
		   }
		   
		   case "instance 010" :
		   {
			   BarChartSat.getData().clear();
				bfsdata.getData().add(new XYChart.Data("1", 133));
				bfsdata.getData().add(new XYChart.Data("2", 135));
				bfsdata.getData().add(new XYChart.Data("3", 136));
				bfsdata.getData().add(new XYChart.Data("4", 136));
				bfsdata.getData().add(new XYChart.Data("5", 137));
				bfsdata.getData().add(new XYChart.Data("6", 136));
				bfsdata.getData().add(new XYChart.Data("7", 137));
				bfsdata.getData().add(new XYChart.Data("8", 136));
				bfsdata.getData().add(new XYChart.Data("9", 137));
				bfsdata.getData().add(new XYChart.Data("10", 135));
				BarChartSat.getData().add(bfsdata);
				
				
				dfsdata.getData().add(new XYChart.Data("1", 291));
				dfsdata.getData().add(new XYChart.Data("2", 297));
				dfsdata.getData().add(new XYChart.Data("3", 298));
				dfsdata.getData().add(new XYChart.Data("4", 297));
				dfsdata.getData().add(new XYChart.Data("5", 298));
				dfsdata.getData().add(new XYChart.Data("6", 300));
				dfsdata.getData().add(new XYChart.Data("7", 295));
				dfsdata.getData().add(new XYChart.Data("8", 298));
				dfsdata.getData().add(new XYChart.Data("9", 300));
				dfsdata.getData().add(new XYChart.Data("10", 301));
				BarChartSat.getData().add(dfsdata);
				
				
				cudata.getData().add(new XYChart.Data("1", 298));
				cudata.getData().add(new XYChart.Data("2", 297));
				cudata.getData().add(new XYChart.Data("3", 298));
				cudata.getData().add(new XYChart.Data("4", 299));
				cudata.getData().add(new XYChart.Data("5", 298));
				cudata.getData().add(new XYChart.Data("6", 297));
				cudata.getData().add(new XYChart.Data("7", 298));
				cudata.getData().add(new XYChart.Data("8", 295));
				cudata.getData().add(new XYChart.Data("9", 297));
				cudata.getData().add(new XYChart.Data("10", 295));
				BarChartSat.getData().add(cudata);
				
				
			
				hdata.getData().add(new XYChart.Data("1", 307));
				hdata.getData().add(new XYChart.Data("2", 309));
				hdata.getData().add(new XYChart.Data("3", 306));
				hdata.getData().add(new XYChart.Data("4", 307));
				hdata.getData().add(new XYChart.Data("5", 312));
				hdata.getData().add(new XYChart.Data("6", 309));
				hdata.getData().add(new XYChart.Data("7", 307));
				hdata.getData().add(new XYChart.Data("8", 310));
				hdata.getData().add(new XYChart.Data("9", 311));
				hdata.getData().add(new XYChart.Data("10", 308));
				BarChartSat.getData().add(hdata);
			   break;
		   }
		   
		   default: 
			   filelabel.setText("Veuillez refaire votre choix !");
			   break;
		   }

	}
    
	
	public void lancer_Astar(ActionEvent event) throws FileNotFoundException
	{
		BarChartSat.setTitle("Nombre de clauses SAT par instances (A*)");
		File file = new File(nominstance); 
		 //       File file= new File("test.cnf");
			    Scanner stdin = new Scanner(file); 
		        int i=0;
		        String s=null;
		        while( i < 8) //sauter les lignes jusqu'a p cnf nbvar nbclauses
		        {	s =stdin.nextLine();
		    //    System.out.println(s);
		        i++;
		        }
		       
		        String[] tab=s.split(" ");
		      int   n = Integer.parseInt( tab[2] );//nombre de variables
		      int   m = Integer.parseInt( tab[4] ); //nbr clauses
		  

				String ss=myMsg.getText();
				if(ss != null ) System.out.println("label msg"+ss);
		    
			Astar_H obj=new Astar_H(n,m);
		    obj.remplir_matrice_clause(nominstance);
		    int max=obj.Parcours(temps);
		    filelabel.setText("Max-SAT A* de "+nominstance+" : "+max);
	}
	
	public void lancer_cost(ActionEvent event) throws FileNotFoundException
	{
		BarChartSat.setTitle("Nombre de clauses SAT par instances (Cost)");
	//	File file = new File("D:/Etudes/M1 SII 2017_2018/S2/Méta-heuristique/Projet_TP/Code/uf75-325/ai/hoos/Shortcuts/UF75.325.100/uf75-06.cnf"); 
		        File file= new File(nominstance);
			    Scanner stdin = new Scanner(file); 
		        int i=0;
		        String s=null;
		        while( i < 8) //sauter les lignes jusqu'a p cnf nbvar nbclauses
		        {	s =stdin.nextLine();
		    //    System.out.println(s);
		        i++;
		        }
		       
		        String[] tab=s.split(" ");
		      int   n = Integer.parseInt( tab[2] );//nombre de variables
		      int   m = Integer.parseInt( tab[4] ); //nbr clauses
		  
		
	    	A_sansH obj=new A_sansH(n,m);
		    obj.remplir_matrice_clause(nominstance);
		    int max=obj.Parcours(temps);
		    filelabel.setText("Max-SAT Cost de "+nominstance+" : "+max);
		    
	}
	
	public void lancer_DFS(ActionEvent event) throws FileNotFoundException
	{
		BarChartSat.setTitle("Nombre de clauses SAT par instances (DFS)");
		File file = new File(nominstance); 
		 //       File file= new File("test.cnf");
			    Scanner stdin = new Scanner(file); 
		        int i=0;
		        String s=null;
		        while( i < 8) //sauter les lignes jusqu'a p cnf nbvar nbclauses
		        {	s =stdin.nextLine();
		    //    System.out.println(s);
		        i++;
		        }
		       
		        String[] tab=s.split(" ");
		      int   n = Integer.parseInt( tab[2] );//nombre de variables
		      int   m = Integer.parseInt( tab[4] ); //nbr clauses
		  
			
		 
		    SAT_DFS obj=new SAT_DFS(n,m);
		    obj.remplir_matrice_clause(nominstance);
		    int max=obj.Parcours(temps);
		    filelabel.setText("Max-SAT DFS de "+nominstance+" : "+max);
	}
	
	public void lancer_BFS(ActionEvent event) throws FileNotFoundException
	{
		BarChartSat.setTitle("Nombre de clauses SAT par instances (BFS)");
		
		File file = new File(nominstance); 
		 //       File file= new File("test.cnf");
			    Scanner stdin = new Scanner(file); 
		        int i=0;
		        String s=null;
		        while( i < 8) //sauter les lignes jusqu'a p cnf nbvar nbclauses
		        {	s =stdin.nextLine();
		    //    System.out.println(s);
		        i++;
		        }
		       
		        String[] tab=s.split(" ");
		      int   n = Integer.parseInt( tab[2] );//nombre de variables
		      int   m = Integer.parseInt( tab[4] ); //nbr clauses
		  
	        SAT_BFS obj=new SAT_BFS(n,m);
		    obj.remplir_matrice_clause(nominstance);
		    int max=obj.Parcours(temps);
		    filelabel.setText("Max-SAT BFS de "+nominstance+" : "+max);
	}
String file;
int test=0;
	public void choose_file(ActionEvent event)
	{
		nominstance= instanceUF.getValue();
	/*	FileChooser fc= new FileChooser();
		File selectedFile = fc.showOpenDialog(null);
		
		if(selectedFile != null)
		{
			//listview.getItems().add(something to show);
			file= selectedFile.getAbsolutePath();
			filelabel.setText("fichier choisi: \n"+selectedFile.getName());
			nominstance=file;
			
		}
		else {
			filelabel.setText("fichier non valide\n");
		}
		*/
		
		
	}

	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
    	uffile.setItems(satfile);
    	uuffile.setItems(satfile);
        tauxmoy.setItems(tauxsat);
	    typefile.setItems(type);
	    instanceUF.setItems(instance);
	}
}

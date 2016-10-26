import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.print.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
public class JPrint implements Printable, ActionListener {
	
	static JTable itemsTable;
    public static  int total_item_count=0;
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss a";
    public  static String title[] = new String[] {"Name","Qty","MRP","RATE","TOTAL"};
    public  static String items1[] = new String[] {"012345678912345678","2","100","100","100"};
    public static String bill_no = "";
    int ycorrdiante = 0;
    public static String totalCNT = "-";
    public static String total = "-";
    
    public static void setItems(Object[][] printitem,HashMap l_Map){
    	//Query part for bill STARTS---
    	Connection conn =null;
    	ResultSet rSet = null;
    	String value = "-";
		String value1 = "-";
		String value2 = "-";
		String value3="-";
		String value4="-";
		String value5="-";
		String value6="-";
		String value7="-";
		String value8="-";
		PreparedStatement psmt = null;
		PreparedStatement psmt1 = null;
		ResultSet rSet1 = null;
		 DefaultTableModel model =null;
		try{
			model = new DefaultTableModel();
		
    	String query = "SELECT IF(BILL_ID=NULL,'-',BILL_ID) BILLNUM,BILL_DATE FROM BILL_INFO WHERE BILL_ID = ?";
    	
    	conn =  DBConnection.getConnection();
		psmt=conn.prepareStatement(query);
		psmt.setString(1,(String)l_Map.get("billNumber"));
		System.out.println("inside bill number is::"+(String)l_Map.get("BILLNUM"));
		rSet = psmt.executeQuery();
		if(rSet!=null){
			while(rSet.next()){
				value  = rSet.getString("BILLNUM");
				value1 = rSet.getString("BILL_DATE");
				System.out.println("inside billnumber::"+value);
			}
		}
		System.out.println("outside billnumber::"+value);
		bill_no = value;
		System.out.println("BIll Number:::"+bill_no);
		//Adding heading to table first
			model.addColumn(title[0]);
	        model.addColumn(title[1]);
	        model.addColumn(title[2]);
	        model.addColumn(title[3]);
	        model.addColumn(title[4]);
		
		  query = "select if(info.item_name=null,'-',SUBSTR(info.item_name,1,16))NAME,if(info.item_qty=null,'-',info.item_qty)QTY,if(info.item_price=null,'-',info.item_price)MRP,if(info.sell_price=null,'-',info.sell_price)ourprice,if(info.billed_amt=null,'-',info.billed_amt)currTotal,if(master.total_iems=null,'-',master.total_iems)totalitems,if(master.bill_amt=null,'-',master.bill_amt) grand from bill_info info,bill_master master where info.BILL_id=master.bill_no order by info.bill_seq";
		     psmt1=conn.prepareStatement(query);
				//psmt.setString(1,(String)l_Map.get("BILLNUM"));
				rSet1 = psmt1.executeQuery();
				ArrayList list1= new ArrayList();
				Vector vect = null;
				if(rSet1!=null){
					while(rSet1.next()){
						System.out.println("result set not empty ..");
						vect = new Vector();
						vect.add(rSet1.getString("NAME"));
						vect.add(rSet1.getString("QTY"));
						vect.add(rSet1.getString("MRP"));
						vect.add(rSet1.getString("ourprice"));
						vect.add(rSet1.getString("currTotal"));
						//vect.add(rSet1.getString("totalitems"));
						//vect.add(rSet1.getString("grand"));
						
						totalCNT = rSet1.getString("totalitems");
		                total = rSet1.getString("grand");
						System.out.println("VECTOR VALUE IS::"+vect);
						System.out.println("VECTOR SIZE IS::"+vect.size());
						 model.addRow(vect);
					}
				}
				System.out.println("total Count is ::"+totalCNT);
				System.out.println("total Amount is ::"+total); 	
    	//Query part for bill ENDS----
        Object data[][]=printitem;
       
       //assume jtable has 4 columns.
       
        
        
       

        int rowcount=printitem.length;
        
        //addtomodel(model, data, rowcount);
       

        itemsTable = new JTable(model);
		}
		catch(Exception ex){
			ex.printStackTrace();
			
		}
}
    
    public static void addtomodel(DefaultTableModel model,Object [][]data,int rowcount){
        int count=0;
        while(count < rowcount){
         model.addRow(data[count]);
         count++;
        }
        if(model.getRowCount()!=rowcount)
          addtomodel(model, data, rowcount);
        
        System.out.println("Check Passed.");
}
    
    public Object[][] getTableData (JTable table) {
        int itemcount=table.getRowCount();
        System.out.println("Item Count:"+itemcount);
        
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int nRow = dtm.getRowCount(), nCol =dtm.getColumnCount();
        Object[][] tableData = new Object[nRow][nCol];
        if(itemcount==nRow)                                        //check is there any data loss.
        {
        for (int i = 0 ; i < nRow ; i++){
            for (int j = 0 ; j < nCol ; j++){
                tableData[i][j] = dtm.getValueAt(i,j);           //pass data into object array.
                }}
         if(tableData.length!=itemcount){                      //check for data losses in object array
         getTableData(table);                                  //recursively call method back to collect data
         }   
        System.out.println("Data check passed");
        }
        else{
                                                               //collecting data again because of data loss.
       getTableData(table);
       }
       return tableData;                                       //return object array with data.
        }
    
    public static PageFormat getPageFormat(PrinterJob pj){
    	System.out.println("Inside PageFormat getPageFormat");
        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();    
             
                double middleHeight =total_item_count*1.0;  //dynamic----->change with the row count of jtable
                double headerHeight = 5.0;                  //fixed----->but can be mod
                double footerHeight = 5.0;                  //fixed----->but can be mod
                
                double width = convert_CM_To_PPI(7);      //printer know only point per inch.default value is 72ppi
                double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
paper.setSize(width, height);
paper.setImageableArea(convert_CM_To_PPI(0.25),convert_CM_To_PPI(0.5),width - convert_CM_To_PPI(0.35),height - convert_CM_To_PPI(1));   //define boarder size    after that print area width is about 180 points
            
            pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
            pf.setPaper(paper);    
            System.out.println("Inside PageFormat getPageFormat : getHeight"+pf.getHeight());
            System.out.println("Inside PageFormat getPageFormat : getImageableHeight"+pf.getImageableHeight());
            System.out.println("Inside PageFormat getPageFormat : getPaper"+pf.getPaper());
            return pf;
            
}
    
    protected static double convert_CM_To_PPI(double cm) {            
        return toPPI(cm * 0.393600787);            
}

protected static double toPPI(double inch) {            
        return inch * 72d;            
}

public static String now() {
	//get current date and time as a String output   
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	return sdf.format(cal.getTime());

	}
 
 
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws
                                                       PrinterException {
    	//Added for testing
    	
    	//added for testing
    	String queryforbillno ="SELECT ITEM_NAME FROM BILL_INFO";
    	PreparedStatement psmt = null;
    	Connection conn =null;
    	ResultSet rSet = null;
    	//String total = "0";
    	
        int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {                    
        Graphics2D g2d = (Graphics2D) graphics; 
       
        double width = pageFormat.getImageableWidth();
        double height = pageFormat.getImageableHeight();    
        g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 
        Font font = new Font("Calibri (Body)",Font.BOLD,8);       
        g2d.setFont(font);
       

        /*try {
       
         * Draw Image*
           assume that printing reciept has logo on top 
         * that logo image is in .gif format .png also support
         * image resolution is width 100px and height 50px
         * image located in root--->image folder 
         
                    int x=100 ;                                        //print start at 100 on x axies
                    int y=10;                                          //print start at 10 on y axies
                    int imagewidth=100;
                    int imageheight=50;
          BufferedImage read = ImageIO.read(getClass().getResource("/image/logo.gif"));
          g2d.drawImage(read,x,y,imagewidth,imageheight,null);         //draw image
          g2d.drawLine(10, y+60, 180, y+60);                          //draw line
                 } catch (IOException e) {
                        e.printStackTrace();
                }*/
        try{
       		/*Draw Header*/
    //int y=80;
      g2d.drawString("<--ESTIMATE-->", 75,10);
      g2d.drawString("SBA STORES", 80,20);
      g2d.drawString("NO-9, GIRI NAGAR", 68,30);
      g2d.drawString("RAMAPURAM, CHENNAI-89", 50,40);
      g2d.drawString("Ph:9840470454",70,50);
      //shift a line by adding 10 to y value
      g2d.drawString("Bill No: "+bill_no, 0,70);
      g2d.drawString("Date:"+now(), 90,70);
      
       

      
      g2d.drawLine(0, 80, 220, 80);
      g2d.drawString(title[0], 0 ,90);
      g2d.drawString(title[1], 90 ,90);
      g2d.drawString(title[2], 110 ,90);
      g2d.drawString(title[3], 140 ,90);
      g2d.drawString(title[4], 175 ,90);
      g2d.drawLine(0, 100, 220, 100);
   
      int cH = 0;
      
      TableModel mod = itemsTable.getModel();
      System.out.println("TABLE MODEL::"+mod.getRowCount());
        
      Font font1 = new Font("Calibri (Body)",Font.BOLD,7);       
      g2d.setFont(font1);
      for(int i = 0;i < mod.getRowCount() ; i++){
    	  if(i==0){
    		  ycorrdiante=110;
    		  }
    	  else{
    		  ycorrdiante =ycorrdiante+20;
    	  }
                /*Assume that all parameters are in string data type for this situation
                 * All other premetive data types are accepted.
                */
    	  System.out.println("getRowCount-->");
                String itemName = mod.getValueAt(i, 0).toString();
                System.out.println("itemid-->"+itemName);
                String qty = mod.getValueAt(i, 1).toString();
                String MRPprice = mod.getValueAt(i, 2).toString();
                String sell_price = mod.getValueAt(i, 3).toString();
                String currentTotal = mod.getValueAt(i, 4).toString();
                //for getting full total amount
                
                //cH = (y+70) + (10*i);                             //shifting drawing line

                g2d.drawString(itemName, 0, ycorrdiante);
                g2d.drawString(qty,90,ycorrdiante);
                g2d.drawString(MRPprice , 110,ycorrdiante);
                g2d.drawString(sell_price , 140,ycorrdiante);
                g2d.drawString(currentTotal , 175,ycorrdiante);
              
        }

        /*Footer*/
      g2d.setFont(font);
        g2d.drawLine(0, ycorrdiante+10, 220, ycorrdiante+10);
        
        g2d.drawString("ITEMS : "+totalCNT , 0,ycorrdiante+20);
        g2d.drawString("GRAND TOTAL(RS.): "+total , 90,ycorrdiante+20);
        font = new Font("Arial",Font.BOLD,12) ;                  //changed font size
        g2d.setFont(font);
        g2d.drawLine(0, ycorrdiante+30, 220, ycorrdiante+30);
        g2d.drawString("Thank You visit Again",30, ycorrdiante+45);
                                                                 //end of the reciept
}
        catch(Exception r){
            r.printStackTrace();
          }

                      result = PAGE_EXISTS;    
                  }    
                  return result;    
    }

 
    private FontMetrics getFontMetrics(Object ftDefault) {
		// TODO Auto-generated method stub
		return null;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("INSIDE ACTION of actionPerformed ");
		 PrinterJob job = PrinterJob.getPrinterJob();
         job.setPrintable(this);
         PageFormat pf = job.defaultPage();
         System.out.println("pageformat::  of actionPerformed "+pf);
         Paper paper = pf.getPaper();
         paper.setImageableArea(0.0*72, 0.0*72, 7.5*72, 10.5*72);
         pf.setPaper(paper);
         System.out.println("getImageableHeight  of actionPerformed "+pf.getImageableHeight());
         System.out.println("getHeight of actionPerformed "+pf.getHeight());  
         System.out.println("getPaper  of actionPerformed "+pf.getPaper());
         Book book = new Book();
         book.append(this,pf);
         job.setPageable(book);
         
         boolean ok = job.printDialog();
         if (ok) {
             try {
                  job.print();
             } catch (PrinterException ex) {
            	 ex.printStackTrace();
              /* The job did not successfully complete */
             }
         }
    }
 
    public boolean  printJob(HashMap l_Map) {
    	try{
    		Object Object[][]=new Object[0][0];
    		setItems(Object,l_Map);
    		System.out.println("INSIDE ACTION of printJob");
             PrinterJob job = PrinterJob.getPrinterJob();
             job.setPrintable(this);
             PageFormat pf = job.defaultPage();
             System.out.println("pageformat::"+pf);
             Paper paper = pf.getPaper();
			 paper.setSize(250,4000);
             paper.setImageableArea(0.0*72, 0.0*72, 7.5*72, 50.0*72);
             
            pf.setPaper(paper);
            System.out.println("Goutham Sai");
            System.out.println("getImageableHeight  of printJob "+pf.getImageableHeight());
            System.out.println("getHeight of printJob "+pf.getHeight());  
            System.out.println("getPaper  of printJob "+pf.getPaper()); 
                     
             Book book = new Book();
             book.append(this,pf);
             job.setPageable(book);
             
            /* boolean ok = job.printDialog();
             if (ok) {*/
                 try {
                      job.print();
                 } catch (PrinterException ex) {
                	 ex.printStackTrace();
                  /* The job did not successfully complete */
                 }
             //}
        return true;
    	}
    	catch(Exception e){
    	return false;
    	}
       /*UIManager.put("swing.boldMetal", Boolean.FALSE);
        JFrame f = new JFrame("Hello World Printer");
        f.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        */
        /*JButton printButton = new JButton("Print Hello World");
        System.out.println("Print Hello World");
        printButton.addActionListener(new JPrint());
        f.add("Center", printButton);
        f.pack();
        f.setVisible(true);*/
    }
    
 
}
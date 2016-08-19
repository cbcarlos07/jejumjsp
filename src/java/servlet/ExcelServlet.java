/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ConnectionFactory;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;




/**
 *
 * @author CARLOS
 */

public class ExcelServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Executando Excel Servlet");
        Connection conn = ConnectionFactory.getConnection();
        //String usuario = req.getParameter("logado");
        ServletContext application = req.getServletContext();
                    try 
            {

                
                String jrxmlFile = application.getRealPath("WEB-INF/rel/print.jrxml");
                InputStream input = new FileInputStream(new File(jrxmlFile));
                JasperDesign jasperDesign = JRXmlLoader.load(input);

                System.out.println("Compiling Report Designs");
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

                System.out.println("Creating JasperPrint Object");
                HashMap map = new HashMap();
                //map.put("CDUSUARIO", usuario);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,conn);
                byte bytes[] = new byte[10000]; 
                JRXlsExporter exporter = new JRXlsExporter();
                ByteArrayOutputStream Stream = new ByteArrayOutputStream(); 
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint); 
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, Stream); 
                exporter.exportReport(); 
                System.out.println("Size of byte array:"+Stream.size()); 
                bytes = Stream.toByteArray(); 
                resp.setContentType("application/ms-excel"); 
                //resp.setContentType("application/ms-excel");
                System.out.println("After JasperPrint = 1"); 
                resp.setContentLength(bytes.length); 
                System.out.println("After JasperPrint = 2"); 
                Stream.close(); 
                System.out.println("After JasperPrint = 3"); 
                //resp.setHeader("Content-Disposition", "attachment; filename=jejum.pdf"); //baixa o relatorio se tirar essa linha apenas exibe na tela
                SimpleDateFormat data = new SimpleDateFormat("dd/MM");
                SimpleDateFormat hora = new SimpleDateFormat("HH");
                SimpleDateFormat minuto = new SimpleDateFormat("mm");
                Date now = new Date();
                
                String fileName = "jejum_"+data.format(now).replace("/", ".")+" "+hora.format(now)+"h"+minuto.format(now)+"min.xls";
                System.out.println("Nome do arquivo: "+fileName);
                resp.setHeader("Content-Disposition", "attachment; filename="+fileName);
                //resp.setHeader("Content-Disposition", "attachment; filename=print1.xls");
                OutputStream outputStream = resp.getOutputStream(); 
                //JasperExportManager.exportReportToPdfStream(jasperPrint,resp.getOutputStream());
                System.out.println("After JasperPrint = 4"); 
                outputStream.write(bytes, 0, bytes.length); 
                outputStream.flush(); 
                outputStream.close(); 

            }
            catch(Exception e) 
            {e.printStackTrace();} 
        
    }
    
}

<%@page import="com.csc.rmg.form.Successform"%>
<%@page import="com.csc.rmg.action.SuccessAction"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.csc.rmg.dao.RMGUserLogin" language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ page import="javax.servlet.http.*" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/vertnav.css"/>
        <link rel="stylesheet" href="../css/jquery-ui-1.9.2.custom.min.css"/>        
        <title>
            Login Success
        </title>         
    </head>       
    <body>
    <img src="images.jpg"/><br/><br/>
    <table style="float:left">
        <tr>
            <th align="left" style="font:bold;font-size:17px;">Welcome :</th>
            <th align="left" style="font:bold;font-size:17px;">
                <%
                    HttpSession hSession = request.getSession(false);
                    String userId = (String) hSession.getAttribute("userid");

                    if (userId == null) {
                        response.sendRedirect("loginPage.jsp");
                    } else {

           
                %>
            </th>
        </tr>
        

    </table><br/>
    <%
            SuccessAction sa=new SuccessAction();
            Successform formBean = new Successform();
            sa.resetForm(formBean);
    %>
    <div style="float:right">
        <html:link style="text-decoration:underline" page="\pages\loginPage.jsp">Logout</html:link>
    </div><br/>
            <hr/>
            <div class="navbox" style="float:left">
            <ul class="nav">
                <li><html:link page="\pages\loginSuccess.jsp"><bean:message key="Applist.al"/></html:link></li>             
                <li><html:link page="\pages\gcarPage.jsp"><bean:message key="Applist.newg"/></html:link></li>
                <li><html:link page="\editGcarByNo.do"><bean:message key="Applist.editg"/></html:link></li>
                <li><html:link page="\pages\compOff.jsp"><bean:message key="Applist.newcomp"/></html:link></li>
                <li><html:link page="\pages\Comp.do"><bean:message key="Applist.compd"/></html:link></li>
                <li><html:link page="\pages\Leave.do"><bean:message key="Applist.monlea"/></html:link></li>
                <li><html:link page="\pages\Other_Month.do"><bean:message key="Applist.omonlea"/></html:link></li>
                <li><html:link page="\pages\changePassword.jsp"><bean:message key="Applist.chpwd"/></html:link></li>
                <li><html:link page="\pages\multiple.do"><bean:message key="Applist.coindate"/></html:link></li>
                <li><html:link page="\pages\RedZone.do"><bean:message key="Applist.rz"/></html:link></li>
                <%
                    if(user.checkUser(userId)){ %>                 
                <li><html:link page="\pages\manager.jsp"><bean:message key="Applist.adminpg"/></html:link></li>
                <% } %>
            </ul>

            </div>
            <div align="center" style="width: 600px;float:left; background-color:#F8F8F8 ">
                <html:form styleId="customForm" action="/Success">
                <table align="" width="534" height="401">

                    <tr><th align="left" style="font:bold;font-size:large">Apply Leave</th><th/></tr>
                    <tr> <td colspan="3" width="220px" style="width:300px;color:red;font:bold;border:0px;background-color:#F8F8F8" >

                            <%
                                String errorMsg = (String) request.getAttribute("errorMsg");
                                if (errorMsg != null) {
                                if(errorMsg=="Leave Inserted Successfully"){ %>
                                    <span style="color: #488AC7"><%=errorMsg%></span>
                               <% }
                                else{  %>
                                <span style="color: red"><%=errorMsg%></span>
                                <%}  
                            }
                            %></td>
                    </tr>
                    <input type="text" id="comphour" name="comphour" style="visibility: hidden" value="<%=hrs%>"/>
                    <tr>
                        <td><bean:message key="applylea.type"/></td>
                        <td align="left"> <select autocomplete="off" name="ap" id="status" onchange="selectEvent('<%=userId%>');">
                                <option value="12"><bean:message key="leavetype.sel"/></option>
                                <option value="0"><bean:message key="leavetype.plan"/></option>
                                <option value="2"><bean:message key="leavetype.app"/></option>
                                <option value="3"><bean:message key="leavetype.comp"/></option>
                            </select>
                        </td>
                        <td>
                            <LABEL name="msg" id="messg" style="visibility:hidden">No CompOff hours</LABEL>
                        </td>
                    </tr>
                    <tr id="comp" style="visibility:hidden">
                        <td><bean:message key="applylea.hours"/></td>
                        <td id="detail" align="left">
                            <label id="comphrs"><%=hrs%></label>
                        </td>                        
                    </tr>
                    <tr>
                        <td><bean:message key="daytype"/></td>
                        <td align="left"> 
                            <bean:message key="optioncomp.hd"/>
                            <html:radio styleId="daytype" property="day" value="HalfDay"></html:radio>
                            <bean:message key="optioncomp.fd"/>                            
                            <html:radio styleId="daytype" property="day" value="FullDay"></html:radio>                                                        
                        </td>                        
                    </tr>                    
                    <tr>
                        <td><bean:message key="applylea.stdate"/></td>
                        <td align="left"> 
                            <html:text  styleId="stdate"  property="startDate" readonly="true"  />                            
                        </td>
                        <td align="left">
                            <html:link page="\holidayAction.do">Holiday List</html:link>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message key="applylea.edate"/></td>
                        <td align="left"> 
                             <html:text styleId="enddate" property="endDate" readonly="true" />                                
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message key="applylea.nodays"/></td>
                        <td align="left">
                            <html:text styleId="nodays" readonly="true" property="noOfDays"/>                            
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message key="applylea.reason" /></td>
                        <td align="left"> <html:textarea styleClass="text" property="reason" /></td>
                    </tr>               
                      
                    <tr>
                        <td align="right"><html:submit property="Submit"/></td>
                        <td> <html:reset property="Reset"/></td>
                    </tr>

                </table>
            </html:form>
        </div>
            
        <%}%>
</body>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript" src="../js/calendarpopup.js"></script>     
</html>

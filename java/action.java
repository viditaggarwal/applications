
public class SuccessAction extends org.apache.struts.action.Action {


    private static final String SUCCESS = "success";
    private final static String FAILURE = "failure";


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       
        RMGUserLogin userLoginDAO = new RMGUserLogin();
        boolean checkdaysflag = false;
        HttpSession session = request.getSession(true);
        String id= (String) session.getAttribute("userid");
        Successform formBean = (Successform) form;
        String noOfDays = formBean.getNoOfDays();
        String startDate = formBean.getStartDate();
        String endDate = formBean.getEndDate();
        String reason = formBean.getReason();
        String ap = request.getParameter("ap");
        String daytype = formBean.getDay();
        String comphour=request.getParameter("comphour"); 
        
        //boolean week = false;

        if (!daytype.isEmpty() && !startDate.isEmpty() && !reason.isEmpty() && !endDate.isEmpty() && !ap.equals("12")) {
            String username = userLoginDAO.getUser(id);
            boolean leaveExistFlag= userLoginDAO.getExistLeave(id,startDate,endDate);
            Integer as = Integer.parseInt(ap);
            System.out.println("no of days"+noOfDays);
            Float nodays=Float.parseFloat(noOfDays);
            Float totalHours=0f;
            if (ap.equals("3")) {
                //String noOfHrs=userLoginDAO.getCompHours(id);
                Float noCompHrs=Float.parseFloat(comphour);
                String tothours=Float.toString(totalHours);
                //String d = request.getParameter("de");
                if (noCompHrs>=totalHours) {
                    if(leaveExistFlag){
                    //System.out.print("inside d equals");          
                    //Date date4 = (Date) format1.parse(d);
                        if ( nodays > 0) {
                                boolean delcompoffFlag = userLoginDAO.deletecompoff(id, tothours);
                                if (delcompoffFlag) {
                                    boolean insertflag = userLoginDAO.insertLeave(id, username, noOfDays, startDate, endDate, reason, as);
                                    if (insertflag) {
                                         String errorMsg = "Leave Inserted Successfully";
                                         request.setAttribute("errorMsg", errorMsg);
                                         resetForm(formBean);
                                         return mapping.findForward(SUCCESS);                                         
                                    } else {
                                         String errorMsg = "Problem with Sql Query";
                                         request.setAttribute("errorMsg", errorMsg);
                                         return mapping.findForward(FAILURE);
                                    }
                                } else {
                                    String errorMsg = "Problem with Delete Operation";
                                    request.setAttribute("errorMsg", errorMsg);
                                    //resetForm(formBean);
                                    return mapping.findForward(FAILURE);
                                }

                        } else {
                                String errorMsg = "No of Days and Diff b/w Start date and End Date not equal";
                                request.setAttribute("errorMsg", errorMsg);
                                //resetForm(formBean);
                                return mapping.findForward(FAILURE);
                        }
                    } else {
                                String errorMsg = "You have already applied for this Date";
                                request.setAttribute("errorMsg", errorMsg);
                                //resetForm(formBean);
                                return mapping.findForward(FAILURE);
                    }
                }else{
                    String errorMsg = "CompOff not available";
                    request.setAttribute("errorMsg", errorMsg);
                    resetForm(formBean);
                    return mapping.findForward(FAILURE);
                }
           }else {
                     
                     if(leaveExistFlag){
                         boolean flag = userLoginDAO.insertLeave(id, username, noOfDays, startDate, endDate, reason, as);
                         if (flag) {
                              return mapping.findForward(SUCCESS);
                         } else {
                              return mapping.findForward(FAILURE);
                         }
                     }else{
                        return mapping.findForward(FAILURE);                         
                     }
                 }                 
        } else {
            return mapping.findForward(FAILURE);
        }
    }

    public void resetForm(Successform formBean) {
        formBean.setEndDate("");
        formBean.setNoOfDays("");
        formBean.setStartDate("");
        formBean.setApplied("");
        formBean.setReason("");
        formBean.setDay("");
    }
}

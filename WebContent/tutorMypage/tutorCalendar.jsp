<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />




<link href="assets/css/fullcalendar.min.css" rel="stylesheet" />
<!-- <link href='../fullcalendar.print.min.css' rel='stylesheet' media='print' /> -->
<script src="assets/js/moment.min.js"></script>
<script src="assets/js/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<script src="assets/js/fullcalendar.min.js"></script>
<script src="assets/js/ko.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<%@include file="../nav.jsp"%>
<link rel="stylesheet" type="text/css" href="assets/css/tab.css">

<title>스타터스 : 튜터링 일정</title>
<head>
<style>
.container {
    width: 80%;
}
div#calendar {
    width: 100%;
    margin: auto;
    margin-top: 50px;
}

</style>


</head>
<div class="container">


  <ul class="nav nav-tabs nav-justified mb-5" id="myTab">
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tutorDetailAction">나의 정보</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tutorTutoringList">튜터링 목록</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tutorTutoringTuteeList">튜터링 일지</a>
    </li>
    <li class="nav-item">
      <a class="nav-link active" href="starters?cmd=tutorTutoringCalenderList1">튜터링 일정</a>
    </li>
  </ul>
  
  
  <div id="calendar"></div>

</div>


	
<%@include file="../../footer.jsp"%>



<!-- 
  <script>
  
  
/*   function test(){
     if('${tutortutoringCalendar.day}'=='월')
  }; */
  

    var events = [<c:forEach items= "${getTutorTutoringCalendarList}" var="tutortutoringCalendar" >
   {title: "${tutortutoringCalendar.title},${tutortutoringCalendar.tuteeId}",
      id:"{tutortutoringCalendar.tutoringId}",
         start : "${tutortutoringCalendar.startTime}",
         end : "${tutortutoringCalendar.endTime}",
         dowstart: new Date("${tutortutoringCalendar.startDate}"),
         dowend: new Date("${tutortutoringCalendar.endDate}")
   },
   </c:forEach>
   ];
/*     events.push({title:"자바웹", start:"10:00", end:"12:00", dow:[4, 5], range:{start:"2018-09-01", end:"2018-12-31"}});
     */
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
      var here =  today = mm+'/'+dd+'/'+yyyy;
    $(document).ready(function() {
        $('#calendar').fullCalendar({
            eventRender: function(event, element, view) {

               var theDate = event.start
               var endDate = event.dowend;
                       var startDate = event.dowstart;

               if (theDate >= endDate) {
                       return false;
               }

               if (theDate <= startDate) {
                 return false;
               }

               },
          header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,basicWeek,basicDay'
          },
          timeFormat : 'hh:mm',
          defaultDate: here,
          navLinks: true, // can click day/week names to navigate views
          editable: true,
          eventLimit: true, // allow "more" link when too many events
               events: events,
          eventClick: function(calEvent, jsEvent, view, resourceObj) {
          /*Open Sweet Alert*/
              swal({
                title: calEvent.title,//Event Title
                text: "Start From : "+moment(calEvent.start).format("MMMM Do YYYY, h:mm a")+"\n"+
                "End to : "+moment(calEvent.end).format("MMMM Do YYYY, h:mm a"),//Event Start Date
                icon: "success",
              });
          },

        });
      });

</script>
  -->
 
<script src="assets/js/tabSizing.js"> </script>
 
<script> 

$(document).ready(function() {
	if($(document).width() < 768){
		verCul();
	}
});

$(window).resize(function (){
	if($(document).width() < 768){
		verCul();
	} else if($(document).width() > 768){
		horCul()}
});

    var colorCode = "#" + Math.round(Math.random() * 0xFFFFFF).toString(16);
   // console.log(colorCode);
    var events = [<c:forEach items= "${getTutorTutoringCalendarList}" var="tutortutoringCalendar" >
    {title: "${tutortutoringCalendar.title},${tutortutoringCalendar.tuteeName}튜티",
          start : "${tutortutoringCalendar.startDate}T${tutortutoringCalendar.startTime}",
           end : "${tutortutoringCalendar.endDate}T${tutortutoringCalendar.endTime}",
          // start : "${tutortutoringCalendar.startDate}T${tutortutoringCalendar.times1}",
         // end : "${tutortutoringCalendar.endDate}T${tutortutoringCalendar.times2}",
        //  dowstart: new Date("${tutortutoringCalendar.startDate}"),
        // dowend: new Date("${tutortutoringCalendar.endDate}"),
        dow: "${tutortutoringCalendar.day}",
    
       // color: "#" + Math.round(Math.random() * 0xFFFFFF).toString(16)
       color: 'hsl(' + Math.round(Math.random() * 360) + ', 100%, 50%)'
       
    },
    
    </c:forEach>
    ];
    
   // events.push({title:"자바웹", start:"10:00", end:"12:00", dow:[4, 5], range:{start:"2018-09-01", end:"2018-12-31"}});
  //  events.push({start:"10:00T2018-09-01", end:"12:00T2018-09-09", title:"자바웹", dow:[4, 5]});

    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
      var here =  today = mm+'/'+dd+'/'+yyyy;
    $(document).ready(function() {
        $('#calendar').fullCalendar({
           defaultDate: moment(),
          header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,basicWeek,basicDay'
          },
          
          timeFormat : 'H(:mm)',
          defaultDate: here,
          navLinks: true, // can click day/week names to navigate views
          editable: false,
          eventLimit: true, // allow "more" link when too many events
               events: events,
          eventClick: function(calEvent, jsEvent, view, resourceObj) {
          /*Open Sweet Alert*/
              swal({
                title: calEvent.title,//Event Title
                text: "시작날짜 / 시작시간 : "+moment(calEvent.start).format("MMMM Do YYYY / a h:mm")+"\n"+
                "종료날짜 / 종료시간 : "+moment(calEvent.end).format("MMMM Do YYYY / a h:mm") +"\n" + //Event Start Date
                "요일 :" +calEvent.dow, 
                icon: "success",
              });
          }
        });
      });

</script>
 
 
 
 
 
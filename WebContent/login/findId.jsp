<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

<!-- 폰트 -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">

<%@include file = "../nav.jsp" %>
<link rel="stylesheet" type="text/css" href="assets/css/login_out/find_id.css">

    
<title>스타터스 : 아이디 찾기</title>

 <div class="col-lg-9 col-md-9 Findtab">
   <ul class="nav nav-pills Findtab" role="tablist">
     <li class="nav-item active">
       <a class="nav-link active" href="starters?cmd=findId">아이디 찾기</a>
     </li>

     <li class="nav-item">
       <a class="nav-link" href="starters?cmd=findpw">비밀번호 찾기</a>
     </li>
   </ul>
 </div>
 
 <div class="login_container">
       <form class="form-horizontal" role="form" method="POST" action="starters?cmd=findIdAction">
			<div class="form">
			<div class="row">
				<div class="col-md-12 h2m">
					<h2>아이디 찾기</h2>
				<h6>아이디는 가입시 입력하신 이메일을 통해 찾을 수 있습니다.</h6>
				</div>
			</div>
           <div class="row">
               <div class="col-md-10 inputbb">
                   <div class="form-group has-danger">
                       <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                           <input type="text" name="name" class="form-control" id="name" placeholder="이름" required autofocus>
                       </div>
                   </div>
               </div>
           </div>
           <div class="row">
               <div class="col-md-10 inputbb">
                   <div class="form-group">
                       <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                         <input type="email" name="email" class="form-control" id="email" placeholder="이메일" required>
                       </div>
                   </div>
               </div>
             </div>
             
             <div class="row">
               <div class="col-md-10 inputbb">
                     <button type="submit" class="btn btn-outline-secondary findbtn">찾기</button>
               </div>
             </div>
             
           </div>
       </form>
   </div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Clean Blog - Start Bootstrap Theme</title>
    <%@ include file="../main/header.jsp"%>
    <!-- Custom fonts for this template-->
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../css/sb-admin-2.min.css" rel="stylesheet">

    <link href="../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
</head>
<body>
<!-- Navigation-->
<jsp:include page="../main/nav.jsp"/>
<!-- Page Header-->
<header class="masthead" style="background-image: url('../assets/img/banner_bg.jpg?after')">
    <div class="container position-relative px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <div class="site-heading">
                    <h1>Members List</h1>
                    <span class="subheading"></span>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Main Content-->
<div class="container px-4 px-lg-5">
    <div class="row gx-4 gx-lg-5 justify-content-center">
        <div class="col-md-10 col-lg-8 col-xl-7">
            <c:forEach items="${requestScope.bloggerList}" var="blogger">
                <!-- Post preview-->
                <div class="post-preview">
                    <a href="../blogger/detail.do?id=${blogger.id}">
                        <h2 class="post-title">${blogger.name}</h2>
                        <h3 class="post-subtitle">${blogger.phone}</h3>
                    </a>
                    <p class="post-meta">
                        Posted by ${blogger.email}
                    </p>
                </div>
                <!-- Divider-->
                <hr class="my-4" />
            </c:forEach>
            <!-- Pager-->
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">

                    <c:if test="${pagination.beginPageNo > pagination.perPagination }" >
                        <li class="page-item">
                            <a class="page-link" href="../blogger/list.do?pn=${pagination.beginPageNo - 1}" tabindex="-1" aria-disabled="true">Prev</a>
                        </li>
                    </c:if>

                    <c:forEach var="pageNo" begin="${pagination.beginPageNo}" end="${pagination.endPageNo}">
                        <c:choose>
                            <c:when test="${pageNo == pagination.curPageNo }">
                                <li class="page-item active"><a class="page-link" href="../blogs/list.do?pn=${pageNo}">${pageNo}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="../blogger/list.do?pn=${pageNo}">${pageNo}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${pagination.endPageNo < pagination.totalPages }" >
                        <li class="page-item">
                            <a class="page-link" href="../blogger/list.do?pn=${pagination.endPageNo + 1}" tabindex="-1" aria-disabled="true">Next</a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>
</div>
<!-- Footer-->
<%@include file="../main/footer.jsp"%>

<!-- Bootstrap core JavaScript-->
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="../js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="../vendor/datatables/jquery.dataTables.min.js"></script>
<script src="../vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="../js/demo/datatables-demo.js"></script>
</body>
</html>

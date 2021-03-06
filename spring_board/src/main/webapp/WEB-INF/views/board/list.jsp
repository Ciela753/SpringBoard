<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="../includes/head.jsp"></jsp:include>
</head>
<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
<jsp:include page="../includes/header.jsp"/>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
					
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Tables</h1>
                    <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
                        For more information about DataTables, please visit the <a target="_blank"
                            href="https://datatables.net">official DataTables documentation</a>.</p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
                            <a href="register" class="btn btn-primary float-right">Register New Board</a>
                        </div>
                        <div class="col-sm-12 col-md-6 d-flex justify-content-end">
                        	<form class="form-inline">
                        		<select name="type" class="custom-select custom-select-sm form-control form-control-sm mr-3">
                        			<option value="T">제목</option>
                        			<option value="C">내용</option>
                        			<option value="W">작성자</option>
                        		</select>
                        		<div id="dataTable_filter" class="input-group">
                        			<input name="keyword" type="search" class="form-control form-control-sm" placeholder="search.."/>
                        			<div class="input-group-append">
                        				<button class="btn btn-primary btn-sm" >
                        					<i class="fas fa-search fa-sm"></i>
                        				</button>
                        			</div>
                        		</div>
                        	</form>
                        </div>
                        
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>번호</th>
                                            <th>제목</th>
                                            <th>작성자</th>
                                            <th>작성일</th>
                                            <th>수정일</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${list}" var="board">
	                                    	<tr>                     	
		                                    	<td><c:out value="${board.bno}"/></td>
		                                    	<td><a href="get${page.cri.params}&bno=${board.bno}"><c:out value="${board.title}"/><b>[${board.replyCnt}]</b></a></td>
		                                    	<td><c:out value="${board.writer}"/></td>
		                                    	<td><fmt:formatDate value="${board.regDate}" pattern="yy-MM-dd"/></td>
		                                    	<td><fmt:formatDate value="${board.updateDate}" pattern="yy-MM-dd"/></td>
	                                    	</tr>
                                    	</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="row">
                        	<div class="col-sm-12 col-md-5">                        		
                        	</div>
                        	<div class="col-sm-12 col-md-7">
                        		<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
                        			<ul class="pagination">
						           		<li class="paginate_button page-item previous  ${page.prev ? '' : 'disabled'}" id ="dataTable_revious">
						           			<a class="page-link" href="list?pageNum=${page.startPage-1}&amount=${page.cri.amount}">Previous</a>
						           		</li>
						           		
						           		<c:forEach begin="${page.startPage}" end="${page.endPage}" var="p">
						           			<li class="page-item ${p == page.cri.pageNum ? 'active' : ''}">
						           				<a class="page-link" href="list?pageNum=${p}&amount=${page.cri.amount}">${p}</a>
						           			</li>
						           		</c:forEach>
						           		<li class="page-item ${page.next ? '' : 'disabled'}">
						           			<a class="page-link" href="list?pageNum=${page.endPage+1}&amount=${page.cri.amount}">Next</a>
						           		</li>
						           	</ul>
                        		</div>
                        	</div>                       
                        </div>
                    </div>
					
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
<jsp:include page="../includes/footer.jsp"/>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->
<script>
$(function() {
	var result = '${result}';
	checkModal(result);
	history.replaceState({}, null, null);
	function checkModal(result) {
		if(!result || history.state) {
			return;
		}
			var text = result == 'success' ? "처리가 완료되었습니다." : "게시글" + result + "번이 등록되었습니다.";
			$("#myModal .modal-body").text("text");
			$("#myModal").modal("show");
		}
	});
</script>
    <!-- List Modal-->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Message</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body"></div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">close</button>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="../includes/foot.jsp" />
</body>

</html>
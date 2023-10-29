<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="es_MX" />

<section id="clients">
    <div class="container">
        <div class="row">
            <!-- Clients list -->
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Clients list</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Balance</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="client" items="${clients}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${client.idClient}</td>
                                    <td>${client.name} ${client.lastname}</td>
                                    <td> <fmt:formatNumber value="${client.balance}" type="currency" /> </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletController?action=edit&idClient=${client.idClient}"
                                           class="btn btn-secondary">
                                            <i class="fas fa-angle-double-right"></i> Edit
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- Lateral total cards -->
            <div class="col-md-3">
                <div class="card text-center bg-danger text-white mb-3">
                    <div class="card-body">
                        <h3>Total balance</h3>
                        <h4 class="display-4">
                            <fmt:formatNumber value="${totalBalance}" type="currency" />
                        </h4>
                    </div>
                </div>
                <div class="card text-center bg-success text-white mb-3"> 
                    <div class="card-body">
                        <h3>Total clients</h3>
                        <h4 class="display-4">
                            <i class="fas fa-users"></i> ${totalClients}
                        </h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Add client MODAL -->
<jsp:include page="/WEB-INF/pages/client/addClient.jsp" />
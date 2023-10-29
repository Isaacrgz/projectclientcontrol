<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Edit client</title>
        <jsp:include page="/WEB-INF/pages/commons/headItems.jsp" />
    </head>
    <body>
        <!-- Header section -->
        <jsp:include page="/WEB-INF/pages/commons/header.jsp" />

        <form action="${pageContext.request.contextPath}/ServletController?action=modify&idClient=${client.idClient}" method="post" class="was-validated">
            <!-- Nav buttons -->
            <jsp:include page="/WEB-INF/pages/commons/navButtonsEdit.jsp" />

            <section id="details">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Edit Client</h4>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="name">Name</label>
                                        <input type="text" class="form-control" value="${client.name}" name="name" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="lastname">Lastname</label>
                                        <input type="text" class="form-control" name="lastname" value="${client.lastname}" required >
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Email</label>
                                        <input type="email" class="form-control" name="email" value="${client.email}" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="phone">Phone</label>
                                        <input type="tel" class="form-control" name="phone" value="${client.phone}" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="balance">Balance</label>
                                        <input type="number" class="form-control" name="balance" value="${client.balance}" required step="any">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            
        </form>

        <!-- Footer section -->
        <jsp:include page="/WEB-INF/pages/commons/footer.jsp" />

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>
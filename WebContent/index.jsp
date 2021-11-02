<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java Pattern</title>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

<link rel="stylesheet" href="css/javaPatterns.css">
<script src="js/javaPatterns.js"></script>

</head>
<body>
    <header>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">
                            Home
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Dropdown </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Another action</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Something else here</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#">Disabled</a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </nav>
    </header>

    <div class="container-fluid">
        <div class="row flex-xl-nowrap">
            <div class="col-12 col-md-3 col-xl-2 bd-sidebar">
                <nav class="bd-links collapse show bg-light" id="navBarLeft">

                    <div class="bd-toc-item active">
                        <a id="behavioral" class="nav-link collapsed" data-toggle="collapse" href="#collapse_behavioral" role="button" aria-expanded="false" aria-controls="collapse_behavioral collapse_implementation_behavioral">Behavioral</a>

                        <ul class="collapse" id="collapse_behavioral">
                        </ul>
                    </div>

                    <div class="bd-toc-item">
                        <a id="creational" class="nav-link collapsed" data-toggle="collapse" href="#collapse_creational" role="button" aria-expanded="false" aria-controls="collapse_creational">Creational</a>

                        <ul class="collapse" id="collapse_creational">
                        </ul>
                    </div>

                    <div class="bd-toc-item">
                        <a id="structural" class="nav-link collapsed" data-toggle="collapse" href="#collapse_structural" role="button" aria-expanded="false" aria-controls="collapse_structural">Structural</a>

                        <ul class="collapse" id="collapse_structural">
                        </ul>
                    </div>

                    <div class="bd-toc-item">
                        <a id="J2EE" class="nav-link collapsed" data-toggle="collapse" href="#collapse_J2EE" role="button" aria-expanded="false" aria-controls="collapse_J2EE">J2EE</a>

                        <ul class="collapse" id="collapse_J2EE">
                        </ul>
                    </div>
                </nav>
            </div>
            <main class="px-4 py-4">
                <div id="description"></div>
                <div id="sample"></div>
            </main>
        </div>
    </div>
</body>
</html>
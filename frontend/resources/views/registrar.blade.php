<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>


    <style body { background: #f8f9fa; } .card {
        border-radius: 15px;
        }
        .btn-primary {
        border-radius: 25px;
        height: 50px;
        font-size: 16px;
        transition: 0.3s;
        }
        .btn-primary:hover {
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
        }>
    </style>
    <link rel="stylesheet" href="{{ asset('css/inicio.css') }}?v={{ time() }}">
</head>

<body>

    <header>
        <nav class="navbar border-0">
            <ul class="nav nav-tabs border-0">
                <li>
                    <a href="{{ route('inicio') }}"><img src="images/logo.png" alt="" class="logo"></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link {{ request()->is('/') ? 'active' : '' }} rounded-pill" href="{{ route('inicio') }}">Inicio</a>
                </li>
                <li class="nav-item transparent-items">
                    <a class="nav-link {{ request()->is('reservaciones') ? 'active' : '' }} rounded-pill" href="{{ route('reservar') }}">Mis vuelos</a>
                </li>
                <li class="nav-item transparent-items">
                    <a class="nav-link {{ request()->is('vuelos') ? 'active' : '' }} rounded-pill" href="{{ route('buscar') }}">Informacion de Vuelos</a>
                </li>
                <li class="nav-item transparent-items">
                    <a class="nav-link {{ request()->is('vuelos') ? 'active' : '' }} rounded-pill" href="{{ route('iniciar-sesion') }}">Iniciar Sesión</a>
                </li>
            </ul>
        </nav>
    </header>
    <div class="container d-flex justify-content-center align-items-center mt-5" style="height: 60vh;">
        <div class="card p-4 shadow-lg" style="width: 420px;">
            <h3 class="text-center mb-3 text-primary fw-bold">Crear Cuenta</h3>
            <p class="text-center text-muted">¡Únete y disfruta de nuestros servicios!</p>

            <form action="{{ route('registro.usuario') }}" method="post">
                @csrf
                @method('POST')
                <div class="mb-3 input-group">
                    <span class="input-group-text bg-primary text-white"><i class="fas fa-user"></i></span>
                    <input type="text" name="nombre" id="nombre" class="form-control" placeholder="Nombre" required>
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text bg-primary text-white"><i class="fas fa-user"></i></span>
                    <input type="text" name="apellido" id="name" class="form-control" placeholder="Apellido" required>
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text bg-primary text-white"><i class="fas fa-envelope"></i></span>
                    <input type="email" name="correo" id="email" class="form-control" placeholder="Correo electrónico" required>
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text bg-primary text-white"><i class="fas fa-lock"></i></span>
                    <input type="password" name="contrasenia" id="contrasenia" class="form-control" placeholder="Contraseña" required>
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text bg-primary text-white"><i class="fas fa-lock"></i></span>
                    <input type="password" name="password_confirmation" id="password_confirmation" class="form-control" placeholder="Confirmar contraseña" required>
                </div>

                <div class="mb-3 form-group">
                    <input type="text" id="fechaNacimiento" name="fechaNacimiento" class="form-control" placeholder="Selecciona tu fecha de nacimiento">
                </div>


                <button type="submit" class="btn btn-primary w-100 fw-bold shadow">
                    Registrarse <i class="fas fa-arrow-right"></i>
                </button>
            </form>

            <div class="text-center mt-3">
                <small class="text-muted">¿Ya tienes cuenta? <a href="{{ route('iniciar-sesion') }}" class="text-primary fw-bold">Inicia sesión</a></small>
            </div>
        </div>
    </div>

    <footer class="derechos">
        <div class="">
            <img src="images/left1.png" class="primary-layer">
            <img src="images/left2.png" class="secondary-layer">
        </div>
        <div class="text-center">
            <p>© 2025 AeroPass. Todos los derechos reservados.</p>
            <p>Desarrollado por Aída, José, Luis, Ronny</p>

        </div>
        <div class="">
            <img src="images/right1.png" class="primary-layer">
            <img src="images/right2.png" class="secondary-layer">
        </div>
    </footer>

    <!-- Bootstrap Bundle con JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <script>
        $(document).ready(function() {
            $("#fechaNacimiento").datepicker({
                dateFormat: "yy-mm-dd", // Formato de la fecha
                changeMonth: true, // Para elegir el mes
                changeYear: true, // Para elegir el año
                yearRange: "-100:+0" // Rango de años (últimos 100 años hasta hoy)
            });
        });
    </script>


</body>

</html>
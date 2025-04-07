<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="{{ asset('css/inicio.css') }}?v={{ time() }}">
</head>

<body>

    <header class="d-flex justify-content-center">
        <ul class="nav nav-tabs border-0">
            <li>
                <a href="{{ route('inicio') }}"><img src="images/logo.png" alt="" class="logo"></a>
            </li>
            <li class="nav-item">
                <a class="nav-link {{ request()->is('/') ? 'active' : '' }} rounded-pill" href="{{ route('inicio') }}">Inicio</a>
            </li>
            <li class="nav-item transparent-items">
                <a class="nav-link {{ request()->is('reservaciones') ? 'active' : '' }} rounded-pill" href="{{ route('historial', 1) }}">Mis vuelos</a>
            </li>
            <li class="nav-item transparent-items">
                <a class="nav-link {{ request()->is('vuelos') ? 'active' : '' }} rounded-pill" href="{{ route('buscar') }}">Informacion de Vuelos</a>
            </li>
            <li class="nav-item transparent-items">
                <a class="nav-link {{ request()->is('vuelos') ? 'active' : '' }} rounded-pill" href="{{ route('iniciar-sesion') }}">Iniciar Sesión</a>
            </li>
        </ul>
    </header>
    <div class="container d-flex justify-content-center align-items-center" style="height: 70vh;">
        <div class="card p-4" style="width: 400px;">
            <h4 class="text-center mb-3">Inicia sesión o regístrate</h4>

            <form action="{{ route('iniciar.sesion') }}" method="post">
                @csrf
                @method('POST')
                <div class="mb-3">
                    <input type="email" name="correo" id="correo" class="form-control" placeholder="Correo electrónico" required style="width: 100%; height: 50px; font-size: 16px;">
                </div>
                <div class="mb-3">
                    <input type="password" name="password" id="password" class="form-control" placeholder="Contraseña" required style="width: 100%; height: 50px; font-size: 16px;">
                </div>
                <button type="submit" class="btn btn-primary w-100" style="border-radius: 25px; height: 50px; font-size: 16px;">Continuar</button>
            </form>

            <div class="text-center mt-3">
                <p class="mb-0">¿No tienes una cuenta? <a href="{{ route('registro.formulario') }}">Regístrate aquí</a></p>
            </div>
        </div>
    </div>

    <footer>
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


    <!-- Bootstrap  -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>
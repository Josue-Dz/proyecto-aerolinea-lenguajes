<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>



    <div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
        <div class="card p-4" style="width: 400px;">
            <h4 class="text-center mb-3">Inicia sesión o regístrate</h4>

            <a href="{{ route('autenticacion.google') }}" class="btn btn-light border w-100 mb-3">
                <img src="https://developers.google.com/identity/images/g-logo.png" width="20" class="me-2">
                Iniciar sesión con Google
            </a>

            <div class="MuiStack-root css-hp68mp" style="display: flex; align-items: center;">
                <hr class="MuiDivider-root MuiDivider-fullWidth css-1e7ihjc" style="flex-grow: 1;">
                <span class="MuiTypography-root MuiTypography-bodyMedium css-1xws29k" style="margin: 0 10px;">o bien</span>
                <hr class="MuiDivider-root MuiDivider-fullWidth css-1e7ihjc" style="flex-grow: 1;">
            </div>

            <form action="" method="POST">
                @csrf
                <div class="mb-3">
                    <input type="email" name="correo" id="correo" class="form-control" placeholder="Correo electrónico" required style="width: 100%; height: 50px; font-size: 16px;">
                </div>
                <div class="mb-3">
                    <input type="password" name="password" id="password" class="form-control" placeholder="Contraseña" required style="width: 100%; height: 50px; font-size: 16px;">
                </div>
                <button type="submit" class="btn btn-primary w-100" style="border-radius: 25px; height: 50px; font-size: 16px;">Continuar</button>
            </form>

            <div class="text-center mt-3">
                <p class="mb-0">¿No tienes una cuenta? <a href="{{ route('registro') }}">Regístrate aquí</a></p>
            </div>
        </div>
    </div>



    <!-- Bootstrap  -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>
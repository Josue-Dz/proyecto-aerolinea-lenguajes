<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    @if(isset($response))
        <h1>Respuesta del backend:</h1>
        <pre>{{ json_encode($response, JSON_PRETTY_PRINT) }}</pre>
    @endif

    @if(isset($error))
        <h1>Error:</h1>
        <p>{{ $error }}</p>
        <h2>Detalles:</h2>
        <pre>{{ $message }}</pre>
    @endif

    
</body>
</html>
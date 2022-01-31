<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
<div align="center">
    <form action="/view" method="POST">
        Photo id: <input type="text" name="photo_id">
        <input type="submit" value="View photo"/>
    </form>
    <form action="/add_photo" enctype="multipart/form-data" method="POST">
        Photo: <input type="file" name="photo">
        <input type="submit" value="Add photo"/>
    </form>
    <input type="submit" value="Get all photos" onclick="window.location='/get_all_photos/';"/>
    <input type="submit" value="Delete many photos" onclick="window.location='/many_del_photos/';"/>
    <input type="submit" value="Link to create zip" onclick="window.location='/create_zip/';"/>
</div>
</body>
</html>

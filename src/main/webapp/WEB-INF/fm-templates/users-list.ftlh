<!DOCTYPE html>
<html>
<head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>

<h2>Users List</h2>

<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>login</th>
        <th>age</th>
        <th>gender</th>
        <th>action</th>
    </tr>
    <#list user as u>
        <tr>
            <td>${u.id}</td>
            <td>${u.name}</td>
            <td>${u.login}</td>
            <td>${u.age}</td>
            <td>${u.gender}</td>
        </tr>
    </#list>

</table>
</body>
</html>
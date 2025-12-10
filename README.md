# ums
user Management System

### Convert HTML → PDF

```
POST /api/convert/pdf
Content-Type: text/plain

<html><body><h1>Hello PDF</h1></body></html>
```

### Convert HTML → Excel XLSX

```
POST /api/convert/excel
Content-Type: text/plain

<table><tr><th>Name</th><th>Qty</th></tr><tr><td>Apple</td><td>5</td></tr></table>
```

Your browser or client will download:

* `output.pdf`
* `output.xlsx`
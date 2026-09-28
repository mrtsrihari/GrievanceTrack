import os, glob

dir_path = r'c:\1 D BACKUP\SEM 3\Javaproject\GrievanceTrack\src\main\resources\static\*.html'
files = glob.glob(dir_path)

for file in files:
    with open(file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    if 'styles.css' not in content:
        content = content.replace('</head>', '    <link href="styles.css" rel="stylesheet">\n</head>')
        content = content.replace('navbar-dark bg-dark', 'navbar-dark')
        content = content.replace('table-dark', '')
        
        with open(file, 'w', encoding='utf-8') as f:
            f.write(content)
        print(f'Updated {file}')

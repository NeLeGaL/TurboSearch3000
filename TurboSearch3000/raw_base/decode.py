out = open('decode.sh', 'w')

lst = open('todecode.txt', 'r')

for filename in lst:
    filename = filename.strip()
    if len(filename) == 0:
        continue
    filename = '"' + filename + '"'

    out.write('iconv -f WINDOWS-1251 -t UTF-8 ' + filename + ' >tmp\nmv tmp ' + filename + '\n')
out.close()


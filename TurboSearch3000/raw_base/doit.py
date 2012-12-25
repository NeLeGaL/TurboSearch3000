import os
import html2text

out = open("result.txt", "w")

def writeDocument(title, category, annotation, text):
    out.write('<div id="' + category + '">\n')
    out.write('\t<h2 id="docTitle" style="">' + title + "</h2>\n")
    out.write('\t<h2 id="docAnnotation" style="">' + title + "</h2>\n")
    out.write('\t<div id="docDate" style="">Sat, 12 Aug 1995 13:30:00 GMT</div>\n')
    out.write(text + '\n')
    out.write("//END_OF_DOCUMENT\n")
    out.write("</div>\n\n")

for dirname, dirnames, filenames in os.walk('.'):
   for filename in filenames:
        fullname = os.path.join(dirname, filename)    
        print "Processing file", fullname

        tokens =  str(os.path.join(dirname, filename)).split('/')
        if len(tokens) != 3:
            continue
        
        need = False
        category = tokens[1].strip()
        title = tokens[2].strip()
        if title.endswith(".htm"):
            title = title[:-len(".htm")]
            need = True
        if title.endswith(".html"):
            title = title[:-len(".html")]
            need = True
        if need == False:
            continue
        
        f = open(fullname, "r")
        fulltext = ""
        for line in f:
            fulltext += line 
#plaintext = html2text.html2text(fulltext)
        writeDocument(title, category, category + ": " + title, fulltext)

out.close()

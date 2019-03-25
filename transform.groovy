cli = new CliBuilder(usage: 'groovy transform.groovy [options]\nTransform to rss feed format the given security advisory')
cli.d(longOpt: 'date', 'Security advisory date to be transformed', required: true, args: 1 )
cli.o(longOpt: 'output', 'Redirect output to a file', required: false , args: 1)
OptionAccessor opt = cli.parse(args)

def advisoryDate = opt.d
def outputFile = opt.o

def writer = new StringWriter()
if (outputFile) {
    writer = new FileWriter(new File(outputFile))
}

def builder = new groovy.xml.MarkupBuilder(writer)
builder.setDoubleQuotes(true)

def content = new File("./advisory/${advisoryDate}/index.html").text

builder.feed(xmlns: "http://www.w3.org/2005/Atom") {
    id "https://www.cloudbees.com/jenkins/security-advisories"
    title(type: "text", "CloudBees Security Advisories")
    subtitle "CloudBees security advisories."
    link(rel: "self", href: "https://raw.githubusercontent.com/cloudbees/security-advisory/master/rss.xml", title: "cloudbees security advisories feed")
    updated('')
    generator 'Automation'
    entry {
        author {
            name 'CloudBees'
        }    
        id "https://raw.githubusercontent.com/cloudbees/security-advisory/master/advisory/${advisoryDate}/index.html"
        title(type: "text", "CloudBees Security Advisory ${advisoryDate}")
        subtitle 'This advisory announces vulnerabilities in Jenkins, CloudBees Jenkins Platform and CloudBees Jenkins Solutions.'
        updated "${advisoryDate}T00:00:00Z"
        summary {
            builder.mkp.yieldUnescaped("""<![CDATA[${content}]]>""")
        }
   }
}

if (!outputFile) {
    println writer
}
# security-advisory

## Transform Security advisories to RSS

```bash
  docker run --rm -ti -v `pwd`:/usr/src/app -w /usr/src/app groovy:2.4-alpine groovy transform.groovy -d <DATE> -o rss.xml
```

Let's print the rss atom feed in the terminal for the security advisory `2019-01-28`

```bash
  docker run --rm -ti -v `pwd`:/usr/src/app -w /usr/src/app groovy:2.4-alpine groovy transform.groovy -d 2019-01-28
```

Let's create a rss.atom.xml file with the security advisory generated on `2019-01-28`:

```bash
  docker run --rm -ti -v `pwd`:/usr/src/app -w /usr/src/app groovy:2.4-alpine groovy transform.groovy -d 2019-01-28 -o rss.atom.xml
```

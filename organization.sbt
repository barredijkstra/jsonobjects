organization in ThisBuild := "nl.salp"
organizationName in ThisBuild := "Barre Dijkstra"
organizationHomepage in ThisBuild := Some(url("https://githubcom/barredijkstra/"))

startYear in ThisBuild := Some(2017)
licenses in ThisBuild += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
homepage in ThisBuild := Some(url("https://github.com/barredijkstra/jsonobjects"))
scmInfo in ThisBuild := Some(ScmInfo(
  browseUrl = url("https://github.com/barredijkstra/jsonobjects"),
  connection = "git@github.com:barredijkstra/jsonobjects"
))
developers in ThisBuild := List(
  Developer(
    id = "barredijkstra",
    name = "Barre Dijkstra",
    email = "dev@salp.nl",
    url = url("https://github.com/barredijkstra")
  )
)

import sbt._

object ShellPrompt {
  object devnull extends ProcessLogger {
    def info(s: => String): Unit = {}
    def error(s: => String): Unit = {}
    def buffer[T](f: => T): T = f
  }

  def currBranch: String = (
    ("git symbolic-ref --short HEAD" lines_! devnull).headOption
      getOrElse "-"
    )

  val buildShellPrompt: (State) => String = state => {
    val project = Project.extract(state).currentRef.project
    s"[$project:$currBranch]> "
  }
}
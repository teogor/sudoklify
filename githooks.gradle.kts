import org.gradle.api.tasks.Copy

tasks.register("updateGitHooks", Copy::class) {
  println("updateGitHooks::Started")

  val gitHooksDir = File("./.git-hooks")
  val hooksDir = File("./.git/hooks")

  gitHooksDir.listFiles()?.forEach { file ->
    println("updateGitHooks::Moving file: ${file.name}")
    copy {
      from(file)
      into(hooksDir)
    }
  }

  println("updateGitHooks::Completed")
}

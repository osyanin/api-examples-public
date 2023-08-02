import io.gatling.recorder.GatlingRecorder;
import io.gatling.recorder.config.RecorderPropertiesBuilder;
import scala.Option;

import java.nio.file.Path;

public class Recorder {
  public static void main(String[] args) {
    RecorderPropertiesBuilder props = new RecorderPropertiesBuilder()
      .simulationsFolder(IDEPathHelper.gradleSourcesDirectory.toString())
      .resourcesFolder(IDEPathHelper.gradleResourcesDirectory.toString())
      .simulationPackage("create_fiat_deposit")
      .simulationFormatJava();

    GatlingRecorder.fromMap(props.build(), Option.<Path> apply(IDEPathHelper.recorderConfigFile));
  }
}

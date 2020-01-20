package vw.meetingroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.codeguruprofilerjavaagent.Profiler;

@SpringBootApplication
@EnableCaching
public class MeetingRoomApplication {

    public static void main(String[] args) {
        Profiler systemProfiler =
                Profiler.builder().profilingGroupName("meeting-room")
                        .awsCredentialsProvider(DefaultCredentialsProvider.create())
                        .build();

        systemProfiler.start();

        SpringApplication.run(MeetingRoomApplication.class, args);
    }

}

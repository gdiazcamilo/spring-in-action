package soundsystem;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TrackerCounterConfig.class })
public class TrackCounterTest {

	@Autowired
	private CompactDisc cd;
	
	@Autowired
	private TrackCounter trackCounter;
	
	@Test
	public void trackerCounterRegisterPlayedTracks() {
		cd.playTrack(1);
		cd.playTrack(2);
		cd.playTrack(3);
		cd.playTrack(3);
		cd.playTrack(3);
		cd.playTrack(1);
		
		Assert.assertEquals(2, trackCounter.getPlayCount(1));
		Assert.assertEquals(1, trackCounter.getPlayCount(2));
		Assert.assertEquals(3, trackCounter.getPlayCount(3));
		
	}
	
}

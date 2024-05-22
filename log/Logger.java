package log;
import java.time.*;
public class Logger{
	private ZoneId zone;
	private Logger(String zoneId){
		this.zone = ZoneId.of(zondId);
	}
	public Logger getLogger(){
		return new Logger("GMT+08:00");
	}
	public Logger getLogger(String zoneId){
		return new Logger(zoneId);
	}
	public void log(String msg){
		var builder = new StringBuilder();
		builder.append("[");
		builder.append(LocalTime.of(zone));
	}
}

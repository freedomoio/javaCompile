package log;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.*;
public class Logger{
	private final ZoneId zone;
	private Logger(String zoneId){
		this.zone = ZoneId.of(zoneId);
	}
	public static Logger getLogger(){
		return new Logger("GMT+08:00");
	}
	public static Logger getLogger(String zoneId){
		return new Logger(zoneId);
	}
	private PrintWriter out = null;
	public void log(String msg){
		var builder = genTime();
		builder.append(Thread.currentThread().getName());
		builder.append("/INFO ");
		builder.append(msg);
		if(out == null) System.out.println(builder);
		else out.println(builder);
	}
	public void setOut(OutputStream os){
		this.out = new PrintWriter(os);
	}
	private StringBuilder genTime(){
		var str = new StringBuilder("[");
		var time = LocalTime.now(zone);
		str.append(time.getHour());
		str.append(":");
		str.append(time.getMinute());
		str.append(":");
		str.append(time.getSecond());
		str.append("] ");
		return str;
	}
	public void err(String msg, Throwable throwable){
		var builder = genTime();
		builder.append(Thread.currentThread().getName());
		builder.append("/ERROR ");
		builder.append(msg);
		System.err.print(builder);
		throwable.printStackTrace(System.err);
	}
}
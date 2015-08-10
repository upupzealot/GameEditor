package panel.weapon.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

public class AnimateData implements JSONString{

	private BufferedImage[] frames;
	public BufferedImage[] getFrames() {
		return frames;
	}
	
	private int frame_count;
	public int getFrameCount() {
		return frame_count;
	}
	
	private int frame_width;
	public int getFrameWidth() {
		return frame_width;
	}
	
	private int frame_height;
	public int getFrameHeight() {
		return frame_height;
	}
	
	private int[] x_offset;
	public int[] getXOffset() {
		return x_offset;
	}
	
	private int[] y_offset;
	public int[] getYOffset() {
		return y_offset;
	}
	
	public AnimateData(File png_file, JSONObject data) throws JSONException, IOException {
		this(png_file, data.getInt("frame_count"));
		JSONArray x_array = data.getJSONArray("x_offset");
		JSONArray y_array = data.getJSONArray("y_offset");
		int frame_count = data.getInt("frame_count");
		for(int i = 0; i < frame_count; i++) {
			x_offset[i] = x_array.getInt(i);
			y_offset[i] = y_array.getInt(i);
		}
	}
	
	public AnimateData(File png_file, int frame_count) throws IOException {
		this(ImageIO.read(png_file), frame_count);
	}
	
	public AnimateData(BufferedImage frame_list, int frame_count) throws IOException {
		this.frame_count = frame_count;
		if(frame_list.getWidth() % frame_count != 0) {
			throw new IOException("图像的宽度不是" + frame_count + "的倍数");
		}
		frame_width = frame_list.getWidth() / frame_count;
		frame_height = frame_list.getHeight();
		
		frames = new BufferedImage[frame_count];
		x_offset = new int[frame_count];
		y_offset = new int[frame_count];
		for(int i = 0; i < frame_count; i++) {
			frames[i] = frame_list.getSubimage(frame_width * i, 0, frame_width, frame_height);
			x_offset[i] = frame_width / 2;
			y_offset[i] = frame_height / 2;
		}
	}

	@Override
	public String toJSONString() {
		JSONObject data = new JSONObject();
		data.put("frame_count", frame_count);
		data.put("x_offset", x_offset);
		data.put("y_offset", y_offset);
		return data.toString();
	}
}

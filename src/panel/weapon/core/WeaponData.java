package panel.weapon.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONString;

import panel.weapon.gui.dialog.FrameCountDialog;

public class WeaponData implements JSONString{
	
	private AnimateData back_fx_data = null;
	public AnimateData getBackFxAnimateData() {
		return back_fx_data;
	}
	private AnimateData weapon_data;
	public AnimateData getWeaponAnimateData() {
		return weapon_data;
	}
	private AnimateData front_fx_data = null;
	public AnimateData getFrontFxAnimateData() {
		return front_fx_data;
	}
	public WeaponData(File png_file) throws IOException {
		String file_name_path = png_file.getParent() + "/" + png_file.getName().substring(0, png_file.getName().length() - 4);
		File data_file = new File(file_name_path + ".data");
		if(!data_file.exists()) {
			FrameCountDialog dialog = new FrameCountDialog(png_file);
			dialog.setVisible(true);
			dialog.MakeCenter();
			while(dialog.isVisible()) {
				//ÔÝÊ±×èÈûÏß³Ì
			}
			int frame_count = dialog.getFrameCount();
			weapon_data = new AnimateData(png_file, frame_count);
			
			File back_fx_file = new File(file_name_path + ".fx.back.png");
			if(back_fx_file.exists()) {
				back_fx_data = new AnimateData(back_fx_file, frame_count);
			}
			
			File front_fx_file = new File(file_name_path + ".fx.front.png");
			if(front_fx_file.exists()) {
				front_fx_data = new AnimateData(front_fx_file, frame_count);
			}
		} else {
			BufferedReader reader = new BufferedReader(new FileReader(data_file));
			JSONObject data = new JSONObject(reader.readLine());
			reader.close();
			
			weapon_data = new AnimateData(png_file, data.getJSONObject("weapon"));
			
			File back_fx_file = new File(file_name_path + ".fx.back.png");
			if(back_fx_file.exists()) {
				back_fx_data = new AnimateData(back_fx_file, data.getJSONObject("fx_back"));
			}
			
			File front_fx_file = new File(file_name_path + ".fx.front.png");
			if(front_fx_file.exists()) {
				back_fx_data = new AnimateData(back_fx_file, data.getJSONObject("fx_front"));
			}
		}
	}
	
	public WeaponData(AnimateData weapon, AnimateData fx_back, AnimateData fx_front) {
		this.weapon_data = weapon;
		this.back_fx_data = fx_back;
		this.front_fx_data = fx_front;
	}
	
	
	@Override
	public String toJSONString() {
		JSONObject data = new JSONObject();
		data.put("weapon", weapon_data);
		data.put("fx_back", back_fx_data);
		data.put("fx_front", front_fx_data);
		return data.toString();
	}
}

package panel.weapon.core;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONString;

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
	public WeaponData(BufferedImage back_fx_image, BufferedImage weapon_image, BufferedImage front_fx_image, int frame_count) throws IOException {
		weapon_data = new AnimateData(weapon_image, frame_count);
		
		back_fx_data = null;
		if(back_fx_image != null) {
			back_fx_data = new AnimateData(back_fx_image, frame_count);
		}
		
		front_fx_data = null;
		if(front_fx_image != null) {
			front_fx_data = new AnimateData(front_fx_image, frame_count);
		}
	}
	
	public WeaponData(AnimateData weapon, AnimateData fx_back, AnimateData fx_front) {
		this.weapon_data = weapon;
		this.back_fx_data = fx_back;
		this.front_fx_data = fx_front;
	}
	
	public static WeaponData importFrom(File weapon_file) throws IOException {
		String file_name_path = weapon_file.getName();
		file_name_path = weapon_file.getParent() + "/" + file_name_path.substring(0, file_name_path.length() - 4);
		File data_file = new File(file_name_path + ".data");
		if(data_file.exists()) {
			
			BufferedReader reader = new BufferedReader(new FileReader(data_file));
			JSONObject data = new JSONObject(reader.readLine());
			reader.close();
			
			AnimateData weapon_data = new AnimateData(weapon_file, data.getJSONObject("weapon"));
			
			AnimateData back_fx_data = null;
			File back_fx_file = new File(file_name_path + ".fx.back.png");
			if(back_fx_file.exists()) {
				back_fx_data = new AnimateData(back_fx_file, data.getJSONObject("fx_back"));
			}
			
			AnimateData front_fx_data = null;
			File front_fx_file = new File(file_name_path + ".fx.front.png");
			if(front_fx_file.exists()) {
				front_fx_data = new AnimateData(front_fx_file, data.getJSONObject("fx_front"));
			}
			
			return new WeaponData(weapon_data, back_fx_data, front_fx_data);
		} else {
			return null;
		}
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

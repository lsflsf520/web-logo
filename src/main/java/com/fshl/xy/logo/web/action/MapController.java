package com.fshl.xy.logo.web.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/map")
public class MapController {
	
	private final static Logger LOG = LoggerFactory.getLogger(MapController.class);
	
//	@Autowired
//	private LocInfoServiceImpl locInfoServiceImpl;
//	
//	@RequestMapping("/locMgr")
//	public String locSearch(){
//		return "map/locMgr";
//	}
//	
//	@RequestMapping("/topos")
//	public String topos(HttpServletRequest request, int locId){
//		LocInfo locInfo = locInfoServiceImpl.findById(locId);
//		request.setAttribute("locInfo", locInfo);
//		return "map/topos";
//	}
//	
//	@RequestMapping("/locdetail")
//	public String tolocDetail(HttpServletRequest request, int locId){
//		LocInfo locInfo = locInfoServiceImpl.findById(locId);
//		request.setAttribute("locInfo", locInfo);
//		return "map/detail";
//	}
//	
//	@RequestMapping("/save")
//	public void save(LocInfo loc, HttpServletRequest request, HttpServletResponse response){
//		locInfoServiceImpl.saveEntity(loc);
//		
//		WebUtils.writeJson("{result:'OK', name:'"+loc.getName()+"'}", request, response);
//	}
//	
//	@RequestMapping("/toLandMap")
//	public String toLandMap(){
//		return "map/landmap";
//	}
//	
//	@RequestMapping("/loadLocInfos")
//	@ResponseBody
//	public List<LocInfo> loadLocInfos(int type){
//		if(type > 0){
//			LocInfo query = new LocInfo();
//			
//			query.setBaseType(type);
//			
//			return locInfoServiceImpl.findByEntity(query);
//		}
//		
//		return locInfoServiceImpl.findAll();
//	}
//
//	@RequestMapping("/getLocs")
//	@ResponseBody
//	public List<LocInfo> getLocs(){
//		List<LocInfo> infos = new ArrayList<LocInfo>();
//		String path = Thread.currentThread().getContextClassLoader().getResource("changsha.txt").getPath();
//		try {
//			List<String> lines = FileUtils.readLines(new File(path), "UTF-8");
//			
//			int type = 0;
//			String city = "";
//			for(String line : lines){
//				if(StringUtils.isBlank(line)){
//					continue;
//				}
//				
//				if(line.startsWith("city=")){
//					city = line.replace("city=", "").trim();
//					continue;
//				}
//				
//				if(line.startsWith("type=")){
//					type = Integer.valueOf(line.replace("type=", "").trim());
//					continue;
//				}
//				
//				String locs[] = line.split("、");
//				for(String loc : locs){
//					LocInfo curr = new LocInfo();
//					curr.setCity(city);
//					curr.setName(loc);
//					curr.setBaseType(type);
//					LocInfo dbLoc = locInfoServiceImpl.findOneByEntity(curr);
//					if(dbLoc == null){
//						infos.add(curr);
//					}
//				}
//			}
//		} catch (IOException e) {
//			LOG.error(e.getMessage(), e);
//		}
//		
//		return infos;
//	}
	
}

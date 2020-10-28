package gaodemap;

import java.util.Arrays;
import java.util.List;

public class ExamPlace {
	
	public String address;
	public String city;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	public static List<String> places = Arrays.asList("河南省许昌市许昌高中",
"河南省许昌市许昌实验中学",
"河南省许昌市二高",
"河南省许昌市一中",
"河南省许昌市许昌县一高",
"河南省许昌市许昌县一高",
"河南省许昌市许昌县三高西校区",
"河南省许昌市鄢陵县一高",
"河南省许昌市鄢陵县一高",
"河南省许昌市鄢陵县二高北区",
"河南省许昌市长葛市一高",
"河南省许昌市长葛市二高",
"河南省许昌市长葛市一中",
"河南省许昌市禹州市一高",
"河南省许昌市禹州市二高",
"河南省许昌市禹州市高级中学",
"河南省许昌市襄城县高中",
"河南省许昌市襄城县实验中学",
"河南省漯河市漯河高中",
"河南省漯河市漯河实验高中",
"河南省漯河市漯河外语中学",
"河南省漯河市漯河二高",
"河南省漯河市漯河高中北校区",
"河南省漯河市漯河五高南校区",
"河南省漯河市漯河四高东校区",
"河南省漯河市漯河五高北校区",
"河南省漯河市漯河四高西校区",
"河南省漯河市舞阳一高",
"河南省漯河市舞阳二高",
"河南省漯河市临颍一高",
"河南省漯河市临颍三高",
"河南省漯河市临颍二高",
"河南省漯河市南街高中",
"河南省开封市二十五中",
"河南省开封市五中",
"河南省开封市河大附中",
"河南省开封市十三中",
"河南省开封市七中",
"河南省开封市开封一高",
"河南省开封市田家炳中学",
"河南省开封市十中",
"河南省开封市十四中",
"河南省开封市立洋外国语中学",
"河南省开封市开封县一中",
"河南省开封市博望高中",
"河南省开封市立洋外国语中学（开封县校区）",
"河南省开封市大同中学",
"河南省开封市县直中学",
"河南省开封市杞县高中",
"河南省开封市北关高中",
"河南省开封市三师附小",
"河南省开封市实验小学",
"河南省开封市尉氏三中南校区",
"河南省开封市尉氏三中北校区",
"河南省开封市通许一高",
"河南省开封市实验小学",
"河南省开封市实验中学",
"河南省开封市兰考一高",
"河南省开封市兰考三高",
"河南省开封市盖亚中学",
"河南省濮阳市濮阳市第一高级中学（老校区）",
"河南省濮阳市濮阳市第二高级中学",
"河南省濮阳市濮阳市综合高中",
"河南省濮阳市濮阳市第一中学",
"河南省濮阳市濮阳市实验中学",
"河南省濮阳市濮阳市第三中学",
"河南省濮阳市濮阳市油田第一高级中学",
"河南省濮阳市濮阳市油田第二高级中学",
"河南省濮阳市濮阳市油田第三高级中学",
"河南省濮阳市濮阳市华龙区高级中学",
"河南省鹤壁市鹤壁高中（东校区）",
"河南省鹤壁市外国语中学",
"河南省鹤壁市兰苑中学",
"河南省鹤壁市淇滨中学",
"河南省鹤壁市鹤壁高中（西校区）",
"河南省鹤壁市一中",
"河南省鹤壁市浚县一中",
"河南省鹤壁市浚县黎阳中学",
"河南省鹤壁市淇县一中",
"河南省济源市济源一中",
"河南省济源市高级中学",
"河南省济源市沁园中学",
"河南省洛阳市河南科技大学附属中学(南校区)",
"河南省洛阳市洛阳市第九中学",
"河南省洛阳市洛阳市第二中学",
"河南省洛阳市洛阳理工学院附属中学",
"河南省洛阳市洛阳市第一职业高中",
"河南省洛阳市洛阳市第二十二中学",
"河南省洛阳市洛阳市第三中学",
"河南省洛阳市洛阳市外国语学校",
"河南省洛阳市洛阳市第十九中学",
"河南省洛阳市洛阳市实验中学",
"河南省洛阳市洛阳市第一中学",
"河南省洛阳市洛阳市回民中学",
"河南省洛阳市洛阳市第一高级中学",
"河南省洛阳市洛阳市第二实验中学(南院)",
"河南省安阳市一中",
"河南省安阳市二中",
"河南省安阳市三中",
"河南省安阳市三十九中",
"河南省安阳市六十六",
"河南省安阳市五中",
"河南省安阳市八中",
"河南省安阳市十中",
"河南省商丘市商丘中学",
"河南省商丘市商丘市实验中学初中部",
"河南省商丘市商丘市第一高级中学",
"河南省商丘市商丘市应天高级中学",
"河南省商丘市商丘市第一高级中学分校",
"河南省商丘市商丘工学院",
"河南省商丘市商丘市第十六中学",
"河南省商丘市商丘市第二高级中学(良浩高中)",
"河南省商丘市商丘市第四高级中学",
"河南省商丘市商丘商贸学校",
"河南省商丘市夏邑县孔祖中等专业学校",
"河南省商丘市夏邑县高级中学南校区",
"河南省商丘市夏邑县高级中学北校区",
"河南省商丘市夏邑县第一高级中学新校区",
"河南省商丘市夏邑县第二实验中学",
"河南省商丘市虞城县高级中学西区",
"河南省商丘市虞城县第二高级中学",
"河南省商丘市虞城县春来高中",
"河南省商丘市柘城县高级中学西校区南区",
"河南省商丘市柘城县高级中学西校区北区",
"河南省商丘市柘城县实验小学",
"河南省商丘市柘城县第二高级中学南区",
"河南省商丘市柘城县第二高级中学北区",
"河南省商丘市宁陵县高级中学东区",
"河南省商丘市宁陵县高级中学西区",
"河南省商丘市宁陵县第二高级中学",
"河南省商丘市睢县高级中学",
"河南省商丘市睢县回族高级中学",
"河南省商丘市睢县职教中心",
"河南省商丘市睢县董店高级中学",
"河南省商丘市民权县高级中学",
"河南省商丘市民权县第一初级中学",
"河南省商丘市民权县第一高级中学",
"河南省商丘市民权县实验小学",
"河南省南阳市南阳市九中",
"河南省南阳市南阳市二十一学校",
"河南省南阳市南阳市二中",
"河南省南阳市南阳市八中",
"河南省南阳市南阳市十二中",
"河南省南阳市南阳市油田第一中学",
"河南省南阳市南阳市第十九中学",
"河南省南阳市南阳市第四中学",
"河南省南阳市南阳市第三中学",
"河南省南阳市南阳市第五中学",
"河南省南阳市南阳市第一中学",
"河南省南阳市南阳市第九小学",
"河南省南阳市镇平县雪枫中学",
"河南省南阳市镇平县第一高级中学",
"河南省南阳市内乡初中",
"河南省南阳市内乡高中",
"河南省南阳市内乡实验初中",
"河南省南阳市西峡二高",
"河南省南阳市西峡一高",
"河南省南阳市淅川中学",
"河南省南阳市淅川县第一高级中学",
"河南省南阳市淅川县第二高级中学",
"河南省南阳市新野县第三高级中学校",
"河南省南阳市新野县第一高级中学校",
"河南省南阳市唐河县友兰实验高中",
"河南省南阳市唐河县第一高级中学",
"河南省南阳市星江中学",
"河南省南阳市唐河县第一小学",
"河南省南阳市桐柏县第一高级中学",
"河南省南阳市桐柏县实验高中",
"河南省南阳市社旗县第二高级中学",
"河南省南阳市社旗县第一高级中学",
"河南省南阳市社旗县职业中专",
"河南省南阳市方城县五高中",
"河南省南阳市方城县一高中",
"河南省南阳市方城机电信息中等职业学校",
"河南省南阳市南召现代中学",
"河南省南阳市南召县第一高级中学",
"河南省南阳市南阳市第十三中学",
"河南省南阳市南阳市第十五小学",
"河南省南阳市南阳市实验中学",
"河南省南阳市南阳市十四中",
"河南省驻马店市驻马店市高级中学老校区",
"河南省驻马店市驻马店市第二高级中学",
"河南省驻马店市驻马店市第三高级中学",
"河南省驻马店市驻马店市第一高级中学",
"河南省驻马店市驻马店市高级中学新校区",
"河南省驻马店市驻马店市第四中学",
"河南省驻马店市驻马店市一高分校",
"河南省驻马店市驻马店市第八初级中学",
"河南省驻马店市确山二高",
"河南省驻马店市确山一高",
"河南省驻马店市泌阳二高北校区",
"河南省驻马店市泌阳二高南校区",
"河南省驻马店市泌阳一高北校区",
"河南省驻马店市泌水中心学校",
"河南省驻马店市泌水二中",
"河南省驻马店市遂平县第一高级中学",
"河南省驻马店市遂平县第二高级中学",
"河南省驻马店市遂平县第一初级中学",
"河南省驻马店市遂平县职教中心",
"河南省驻马店市西平县杨庄高中",
"河南省驻马店市西平县高级中学",
"河南省驻马店市西平县职业教育中心",
"河南省驻马店市上蔡县第二高级中学",
"河南省驻马店市上蔡第一初级中学",
"河南省驻马店市上蔡县第二小学",
"河南省驻马店市上蔡二高南校区",
"河南省驻马店市上蔡第二初级中学",
"河南省驻马店市上蔡一高西区",
"河南省驻马店市上蔡县第三小学",
"河南省驻马店市上蔡县苏豫中学",
"河南省驻马店市上蔡一高北区",
"河南省驻马店市上蔡县第一小学",
"河南省驻马店市汝南县第二高级中学",
"河南省驻马店市汝南双语学校",
"河南省驻马店市汝南县高级中学",
"河南省驻马店市汝南县高级中学",
"河南省驻马店市第三高级中学",
"河南省驻马店市实验中学",
"河南省驻马店市第一高级中学",
"河南省驻马店市第二高级中学",
"河南省驻马店市正阳县高级中学",
"河南省驻马店市正阳县第二高级中学",
"河南省驻马店市正阳县第一高级中学",
"河南省驻马店市正阳职业中专",
"河南省郑州市郑州市第七中学初中部",
"河南省郑州市郑州市第七十一中学",
"河南省郑州市郑州市第四十九中学经八路26号",
"河南省郑州市郑州市第六十一中学",
"河南省郑州市郑州经济贸易学校",
"河南省郑州市郑州市第一中学",
"河南省郑州市郑州市第十六中学",
"河南省郑州市郑州市第十九中学",
"河南省郑州市郑州市第二十四中学",
"河南省郑州市郑州市外国语学校",
"河南省郑州市郑州第一中学实验分校",
"河南省郑州市郑州市第五十一中学",
"河南省郑州市郑州市第五十二中学",
"河南省郑州市郑州市第七十三中学",
"河南省郑州市郑州市第八十中学",
"河南省郑州市郑州市第八十九中学",
"河南省郑州市郑州金融学校",
"河南省郑州市郑州市财经技师学院",
"河南省郑州市郑州市第二中学",
"河南省郑州市郑州市第四中学",
"河南省郑州市郑州市第五中学",
"河南省郑州市郑州市第四十四中学",
"河南省郑州市郑州市第七十四中学",
"河南省郑州市郑州市第一零一中学",
"河南省郑州市郑州市第一零二中学",
"河南省郑州市郑州市第一零六中学",
"河南省郑州市郑州市第一零七中学",
"河南省郑州市郑州市第二十二中学",
"河南省郑州市郑州市第五十七中学",
"河南省郑州市郑州市第八十一中学",
"河南省郑州市郑州市第八十二中学",
"河南省郑州市郑州市艺术工程学校",
"河南省郑州市郑州市回民中学",
"河南省郑州市郑州市为民高中",
"河南省郑州市郑州市第四十五中",
"河南省郑州市郑州市商贸管理学校",
"河南省郑州市郑州旅游职业学院",
"河南省郑州市郑州市信息技术学校",
"河南省郑州市郑州市第七中学高中部",
"河南省郑州市郑州市第九中学",
"河南省郑州市河南省实验中学",
"河南省郑州市郑州市第三十一中学",
"河南省郑州市郑州市第一百零三中学",
"河南省郑州市郑州市第七十七中学",
"河南省郑州市郑州市科技工业学校",
"河南省郑州市郑州市第四中学北校区",
"河南省郑州市郑州市第十二中学",
"河南省郑州市郑州市实验高级中学",
"河南省郑州市郑州市第四十七中学",
"河南省郑州市郑州市第二外国语学校",
"河南省郑州市荥阳市第二高级中学",
"河南省郑州市荥阳市第三初级中学",
"河南省郑州市荥阳市索河中学",
"河南省郑州市荥阳市实验高中",
"河南省郑州市荥阳市高级中学",
"河南省郑州市荥阳市第一初级中学",
"河南省郑州市新密第一高级中学",
"河南省郑州市新密第二高级中学",
"河南省郑州市新密实验高级中学",
"河南省郑州市新密市职教中心",
"河南省郑州市登封市第一高级中学",
"河南省郑州市登封市实验高级中学",
"河南省郑州市登封市嵩阳高级中学",
"河南省郑州市登封市嵩阳中学",
"河南省郑州市登封市第一初级中学",
"河南省郑州市新郑市第一中学",
"河南省郑州市新郑市第二中学",
"河南省郑州市新郑市第三中学",
"河南省郑州市新郑市第一中学分校",
"河南省郑州市新郑市第二中学分校",
"河南省郑州市新郑市中等专业学校",
"河南省郑州市中牟第一高级中学东区",
"河南省郑州市中牟第一高级中学西区",
"河南省郑州市中牟县第二高级中学",
"河南省郑州市中牟县第四高级中学",
"河南省郑州市中牟职业中等专业学校",
"河南省周口市周口一高",
"河南省周口市周口二高",
"河南省周口市周口三高",
"河南省周口市周口第十八初级中学",
"河南省周口市周口第十九初级中学",
"河南省周口市周口第二十二初级中学",
"河南省周口市周口市纺织路小学",
"河南省周口市周口科技职业学院",
"河南省信阳市七中",
"河南省信阳市浉河中学",
"河南省信阳市信高分校",
"河南省信阳市九中春华分校",
"河南省信阳市六高",
"河南省信阳市平桥区二小",
"河南省信阳市二高",
"河南省信阳市一中",
"河南省信阳市实验小学",
"河南省信阳市五中",
"河南省新乡市一中",
"河南省新乡市二中",
"河南省新乡市三中",
"河南省新乡市四中",
"河南省新乡市附中",
"河南省新乡市铁路一中",
"河南省新乡市三十中",
"河南省新乡市二十二中",
"河南省新乡市十中",
"河南省新乡市八中",
"河南省焦作市焦作市第1中学",
"河南省焦作市焦作市第12中学",
"河南省焦作市焦作市龙源湖学校",
"河南省焦作市焦作市第11中学",
"河南省焦作市焦作市实验中学",
"河南省焦作市焦作市实验小学",
"河南省焦作市焦作市光明中学",
"河南省焦作市焦作市外国语中学");
}
/**
 * 实现功能：主页
 *
 * @author cs
 * @version 1.0.00
 */
EUI.HomeView = EUI.extend(EUI.CustomUI, {
    renderTo: "",
    authority: "",
    initComponent: function () {
        this.container = EUI.Container({
            renderTo: this.renderTo,
            layout: "border",
            padding: 0,
            isOverFlow: false,
            itemspace: 0,
            defaultConfig: {
                border: false
            },
            items: [{
                region: "north",
                height: 56,
                padding: 0,
                style: {
                    background: "#0071BB",
                    color: "white"
                },
                xtype:"Container",
                isOverFlow: false,
                html: this.getTopHtml()
            }, this.getCenter(), {
                region: "west",
                padding: 0,
                id: "westTree",
                width: 210,
                xtype:"Container",
                isOverFlow: false,
                html: '<div class="side-menu">'+
                        '<div class="menu">'+
                            '<ul>'+
                            '</ul>'+
                        '</div>'+
                      '</div>'
            }]
        });

        this.getMenus();
        this.addEvents();
    },
    getTopHtml:function(){
        return ['<div class="main-menu">',
            '       <div class="logo">',
            '           <span id="showmenu" >' ,
            '               <svg t="1524634712468" class="icon" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1421" data-spm-anchor-id="a313x.7781069.0.i0" xmlns:xlink="http://www.w3.org/1999/xlink" width="50" height="50"><defs><style type="text/css"></style></defs><path d="M909.37856 669.64992c-0.37888-0.65024-0.77824-1.32096-1.16224-2.00192a131.27168 131.27168 0 0 0-1.43872-2.36032c-0.31232-0.4608-0.59904-0.93184-0.91136-1.39776-0.6144-0.97792-1.29024-1.93024-1.94048-2.88768-0.26112-0.37888-0.53248-0.74752-0.78336-1.09056a113.37728 113.37728 0 0 0-4.64896-5.90848c-0.11264-0.13824-0.26112-0.29184-0.384-0.45056a78.336 78.336 0 0 0-3.09248-3.39968c-0.12288-0.14848-0.27648-0.29184-0.39936-0.44032a100.88448 100.88448 0 0 0-9.64096-8.80128 388.42368 388.42368 0 0 0 15.0528-107.3408c0-180.23936-122.89024-331.76576-289.44896-375.39328-0.37376-0.8192-0.79872-1.63328-1.19808-2.45248-0.19968-0.3584-0.3584-0.73216-0.55808-1.09056a101.03808 101.03808 0 0 0-1.29024-2.3808c-0.12288-0.23552-0.27648-0.49152-0.39936-0.72704-0.87552-1.54112-1.78688-3.0208-2.72384-4.50048a17.47968 17.47968 0 0 0-0.58368-0.88064 52.31616 52.31616 0 0 0-1.48992-2.16576 13.0816 13.0816 0 0 0-0.72704-1.07008 103.0144 103.0144 0 0 0-5.20192-6.62016c-0.32768-0.36864-0.64-0.7424-0.98304-1.10592-0.52736-0.60928-1.0752-1.19808-1.59744-1.80224l-1.1264-1.14688a79.5392 79.5392 0 0 0-2.78528-2.7904c-0.45568-0.4352-0.8704-0.86528-1.32608-1.27488a94.1312 94.1312 0 0 0-1.65376-1.4848l-1.41824-1.22368c-0.54272-0.44544-1.09056-0.91648-1.63328-1.37216-0.63488-0.52736-1.3056-1.02912-1.96608-1.536-0.7424-0.5888-1.50528-1.13664-2.26816-1.69984-0.63488-0.4608-1.26976-0.91648-1.90976-1.34656-0.52736-0.37376-1.05472-0.7168-1.5872-1.07008-0.65024-0.44544-1.34144-0.8704-2.00192-1.28-0.49152-0.31744-0.9728-0.60928-1.46944-0.91648a97.08544 97.08544 0 0 0-3.27168-1.88416c-0.4608-0.24576-0.93184-0.48128-1.36704-0.73728-0.79872-0.4096-1.5872-0.8192-2.38592-1.20832-0.4864-0.23552-0.97792-0.47104-1.4848-0.72704-0.95232-0.44544-1.9456-0.89088-2.92864-1.32096l-0.93184-0.41984c-1.21344-0.512-2.45248-0.9728-3.67104-1.44384l-1.34656-0.46592a66.9184 66.9184 0 0 0-2.85696-0.9728 116.82816 116.82816 0 0 1-1.23392-0.41984c-1.3312-0.4096-2.65728-0.77312-3.99872-1.1264-0.27136-0.05632-0.54784-0.13312-0.85504-0.18944a78.82752 78.82752 0 0 0-4.69504-1.05472 103.0144 103.0144 0 0 0-3.4304-0.62976c-0.29184-0.04608-0.58368-0.1024-0.89088-0.13824a100.41856 100.41856 0 0 0-4.29568-0.54272c-0.36352-0.04608-0.74752-0.08192-1.10592-0.11776-1.152-0.1024-2.2784-0.17408-3.42016-0.23552-0.41984-0.03584-0.83968-0.05632-1.25952-0.08192a87.45984 87.45984 0 0 0-8.91904 0 90.56256 90.56256 0 0 0-5.09952 0.35328c-0.14336 0.01536-0.32256 0.01536-0.47616 0.03584-1.44896 0.16384-2.87232 0.35328-4.29056 0.55296l-1.1264 0.17408c-1.18272 0.18944-2.3808 0.4096-3.52768 0.64512a68.89472 68.89472 0 0 0-4.86912 1.09056c-0.384 0.08192-0.74752 0.18944-1.1264 0.29184-1.07008 0.29184-2.14528 0.57856-3.18464 0.91648l-1.01888 0.29184c-1.3056 0.4096-2.58048 0.8448-3.85536 1.3056-0.36352 0.11776-0.71168 0.27136-1.08544 0.4096-0.96256 0.37376-1.9456 0.74752-2.91328 1.152-0.384 0.1536-0.79872 0.31232-1.16224 0.48128-1.21856 0.512-2.40128 1.05472-3.59936 1.62816-0.36352 0.1536-0.70656 0.34304-1.09056 0.52736-0.87552 0.42496-1.74592 0.8704-2.60096 1.34656a38.28736 38.28736 0 0 0-1.29024 0.70144c-1.14176 0.59904-2.23744 1.2288-3.32288 1.8944-0.34816 0.21504-0.6912 0.45568-1.05984 0.66048-0.79872 0.48128-1.5616 0.9984-2.36032 1.50016l-1.34656 0.93696c-0.85504 0.5632-1.65376 1.13664-2.45248 1.7152-0.54784 0.38912-1.0752 0.8192-1.59744 1.20832-0.71168 0.54784-1.41824 1.09056-2.12992 1.68448-0.47104 0.36352-0.91136 0.73728-1.3824 1.1264-0.61952 0.52736-1.2544 1.07008-1.85344 1.61792-0.59904 0.52736-1.23392 1.09056-1.83296 1.65376-0.61952 0.59392-1.27488 1.19808-1.8944 1.80224-0.45056 0.47104-0.90624 0.91648-1.36192 1.39264a60.37504 60.37504 0 0 0-3.2 3.456c-0.59904 0.6656-1.19808 1.33632-1.77664 2.02752-0.44032 0.52736-0.87552 1.07008-1.31072 1.59744-0.39936 0.512-0.8192 1.00864-1.19808 1.536-0.41984 0.52736-0.83456 1.08032-1.2544 1.61792-0.65536 0.90112-1.31072 1.8176-1.93024 2.73408-0.41984 0.5888-0.78336 1.17248-1.16736 1.75616-0.36352 0.52736-0.68608 1.05472-1.01888 1.58208-0.384 0.60928-0.76288 1.20832-1.10592 1.8176-0.58368 0.95744-1.13152 1.90976-1.67936 2.88256v0.01536C258.98496 187.27936 123.9552 344.62208 123.9552 533.56544a387.87584 387.87584 0 0 0 15.0528 107.3408c-2.0736 1.66912-4.09088 3.39968-6.03648 5.2224-0.02048 0.03072-0.05632 0.09216-0.09216 0.128-1.21856 1.12128-2.36032 2.26816-3.49184 3.45088l-0.41984 0.44032a62.7712 62.7712 0 0 0-3.072 3.40992c-0.14848 0.14848-0.256 0.30208-0.39936 0.44032-1.61792 1.90976-3.18464 3.8912-4.6592 5.90848a191.44192 191.44192 0 0 0-2.74432 3.97824c-0.29184 0.47104-0.59904 0.93184-0.89088 1.39776-0.49152 0.77824-0.98304 1.58208-1.4336 2.36032-0.39936 0.68096-0.76288 1.35168-1.16736 2.00192-0.29184 0.56832-0.59904 1.11104-0.89088 1.65888-0.512 0.94208-0.98304 1.88928-1.45408 2.8416-0.256 0.50176-0.45568 0.9984-0.70656 1.50016-0.41984 0.88064-0.79872 1.73056-1.19808 2.64192-0.22016 0.512-0.4352 1.05984-0.65536 1.59744-0.39936 0.91136-0.74752 1.83808-1.09056 2.75968-0.16384 0.39936-0.3072 0.77824-0.45056 1.17248-0.39936 1.1776-0.8192 2.33984-1.18272 3.52768-0.128 0.37888-0.23552 0.75776-0.36352 1.16224-0.36352 1.19808-0.74752 2.44224-1.05472 3.67104-0.0768 0.32768-0.14848 0.65024-0.256 0.96768a133.5296 133.5296 0 0 0-0.89088 3.95776c-0.03584 0.13824-0.0512 0.29184-0.09216 0.44032a138.2656 138.2656 0 0 0-0.8192 4.74112c-0.22016 1.3824-0.39936 2.75968-0.52736 4.17792-0.05632 0.26112-0.07168 0.54784-0.10752 0.83968-0.128 1.41824-0.256 2.82112-0.3072 4.2496A112.05632 112.05632 0 0 0 102.4 716.8c0 53.5808 43.43296 97.01888 97.01376 97.01888 1.65376 0 3.29216-0.04096 4.92544-0.14848 0.65536 0 1.32608-0.09216 1.98144-0.128 0.96256-0.09216 1.90976-0.11776 2.85696-0.22016 0.85504-0.09216 1.71008-0.20992 2.58048-0.32768 0.72704-0.08192 1.45408-0.15872 2.18112-0.27136 0.98304-0.13824 1.92512-0.32768 2.88768-0.50176 0.59904-0.10752 1.19808-0.19968 1.78176-0.32768 1.05472-0.19968 2.0736-0.4608 3.12832-0.70144 0.49152-0.1024 0.98304-0.20992 1.48992-0.33792 1.0752-0.27136 2.14528-0.55808 3.23584-0.89088 0.39936-0.128 0.83968-0.22016 1.27488-0.36864 1.09056-0.31744 2.2016-0.70144 3.29216-1.08032 0.34816-0.11776 0.74752-0.24064 1.11104-0.36864 1.10592-0.39936 2.2016-0.82944 3.27168-1.26976 0.31232-0.10752 0.5632-0.19968 0.85504-0.32768C306.59072 877.59872 404.15232 921.6 512.00512 921.6c107.84768 0 205.40928-44.00128 275.73248-115.03104 0.27648 0.128 0.5632 0.19968 0.83968 0.32768 1.09056 0.41984 2.18112 0.84992 3.2768 1.24928 0.37888 0.128 0.75776 0.25088 1.1264 0.39936 1.11104 0.36864 2.2016 0.73216 3.30752 1.0496 0.41472 0.14848 0.83456 0.24064 1.26976 0.38912 1.0752 0.31232 2.14528 0.59904 3.22048 0.8704 0.512 0.128 0.9984 0.24064 1.5104 0.33792 1.01376 0.26112 2.048 0.50176 3.08736 0.72192 0.58368 0.11776 1.19808 0.19968 1.80224 0.31232 0.96256 0.16896 1.91488 0.3584 2.8928 0.50176 0.70656 0.10752 1.45408 0.18944 2.2016 0.27136 0.85504 0.128 1.6896 0.24064 2.54464 0.31744 0.9472 0.10752 1.92512 0.15872 2.85696 0.2304 0.65536 0.04096 1.3312 0.128 2.00192 0.128 1.63328 0.10752 3.27168 0.14848 4.90496 0.14848 53.58592 0 97.01888-43.43808 97.01888-97.01888l-12.22144-47.1552z m-681.25184-45.5424a97.16224 97.16224 0 0 0-28.70784-4.33152c-5.28896 0-10.4704 0.4608-15.54432 1.25952a339.9168 339.9168 0 0 1-11.40224-87.47008c0-157.80352 107.63264-290.41664 253.48608-328.5248a96.49152 96.49152 0 0 0 20.33152 53.98016l-218.1632 365.08672z m283.88352 248.98048c-91.99616 0-175.45216-36.59776-236.5952-96.04096a96.7168 96.7168 0 0 0 15.47776-27.88864h442.21952a96.6144 96.6144 0 0 0 15.49312 27.88864c-61.14304 59.4432-144.59392 96.04096-236.5952 96.04096z m216.19712-167.06048H295.82848a96.62464 96.62464 0 0 0-30.4128-60.27776l214.67136-359.22432a96.75776 96.75776 0 0 0 42.68544 9.9072c14.23872 0 27.72992-3.08224 39.9104-8.58112l205.1584 350.27968c-21.63712 15.64672-36.56704 39.97696-39.63392 67.89632z m111.93856-84.98688a95.37536 95.37536 0 0 0-32.76288 0.31232l-210.49856-359.36768a96.68096 96.68096 0 0 0 22.16448-50.71872c135.0656 44.84608 232.48384 172.19584 232.48384 322.304a341.1456 341.1456 0 0 1-11.38688 87.47008z" fill="#48B9FF" p-id="1422"></path></svg>',
            '           </span>',
            '           <span>API网关</span>',
            '           <div class="main-title"></div>',
            '       </div>',
            '            <div class="" style="display: inline-block;float: right;margin-top: 10px;">',
            '                <div class="" style="display: inline-block;border-right: 1px solid #fff;">',
            '                    <span style="font-size: 16px;margin-right: 10px;color: #fff;" th:inline="text">'+remoteUser+'</span>',
            '                </div>',
            '                <div class="" style="display: inline-block;margin-right: 20px;margin-left: 10px">',
            '                    <form action="" method="post">',
            '                        <input style="background: transparent;border: none;color: #fff;cursor: pointer;" type="submit" value="退出"/>',
            '                    </form>',
            '                </div>',
            '            </div>',
            '        </div>'].join("");
    },
    getCenter:function(){
        return {
            region: "center",
            xtype: "TabPanel",
            id: "tabPanel",
            maxTabs: 15,//最多可以开15个页签
            isOverFlow: false,
            onActive: function (id, win) {
                if(id.indexOf("tabitem")==0){
                    $(".main-title").html("首页");
                    $(".menu").find("li").removeClass('menu-select');
                }else{
                    var tab = $("#"+id);
                    $(".main-title").html(tab.attr("mainTitle"));
                    $(".menu").find("li").removeClass('menu-select');
                    tab.addClass("menu-select");
                }
            },
            items: [{
                title: "首页",
                closable: false,
                url: _ctxPath + "/homePage"
            }]
        };
    },
    getMenus:function(){
        var g = this;
        $.ajax({
            type: "POST",
            url: _ctxPath + "/getMenus",
            success: function(menuDatas){
                var menuWrapper = $(".menu ul");
                menuDatas.forEach(function (menuData) {
                    menuWrapper.append('<li url="' + _ctxPath + menuData.url +'" id="'+ menuData.path + '" mainTitle="' + menuData.title +'">' +
                        '<a>' +
                        '<i class="' + menuData.iconClass + '" style="display: none" title="'+ menuData.name +'"></i>' +
                        '<span class="text" style="">'+  menuData.name + '</span>' +
                        '</a>' +
                        '</li>');
                });
                $(".menu li").click(function(){
                    $(".main-title").html($(this).attr("mainTitle"));
                    $(".menu").find("li").removeClass('menu-select');
                    $(this).addClass("menu-select");

                    var url = $(this).attr("url");
                    var mainTitle = $(this).attr("mainTitle");
                    var name = mainTitle.substring(mainTitle.indexOf("【")+1,mainTitle.indexOf("】"));
                    var tab = {
                        title: name,
                        url: url,
                        id: $(this).attr("id")
                    };
                    g.addTab(tab);

                });
            }
        });
    },

    addEvents:function () {

    },
    addTab: function (tab, listenner) {
        var tabPanel = this.getTabPanel();
        tabPanel.addTab(tab);
        if (!tab.id || !listenner) {
            return;
        }
        this.tabListener[tab.id] = listenner;
    },
    getTabPanel: function () {
        return EUI.getCmp("tabPanel");
    }


});
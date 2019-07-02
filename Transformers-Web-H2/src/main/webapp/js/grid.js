/**
 * 
 */
Ext.require([
	    'Ext.window.MessageBox',
	    'Ext.tip.*',
	    'Ext.data.*', 'Ext.grid.*'
	]);
	Ext.define('Transformer', {
	    extend: 'Ext.data.Model',
	    fields: ['id', 'name', 'type','strength','intelligence','speed','endurance','rank','courage','firepower','skill']
	});

	Ext.onReady(function(){
		var store = Ext.create('Ext.data.Store', {
		        autoLoad: true,
		        autoSync: true,
		        model: 'Transformer',
		        proxy: {
		            type: 'rest',
		            url: '/battleground/transformers',
		            actionMethods:  {
		                create: "POST",
		                read: "GET",
		                update: "PATCH",
		                destroy: "DELETE"
		            },
		            reader: {
		                type: 'json',
		                root: 'data'
		            },
		            writer: {
		                type: 'json'
		            }
		        },
		        listeners: {
		            write: function(store, operation){
		                var record = operation.getRecords()[0],
		                    name = Ext.String.capitalize(operation.action),
		                    verb;
		                    
		                    
		                if (name == 'Destroy') {
		                    record = operation.records[0];
		                    verb = 'Destroyed';
		                } else {
		                    verb = name + 'd';
		                }
		                Ext.Msg.alert(name, Ext.String.format("{0} transformer: {1}", verb, record.getId()));
		            }
		        }
		    });
	    
			var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
		        listeners: {
		            cancelEdit: function(rowEditing, context) {
		                // Canceling editing of a locally added, unsaved record: remove it
		                if (context.record.phantom) {
		                    store.remove(context.record);
		                }
		            }
		        }
		    });
		    
			var grid = Ext.create('Ext.grid.Panel', {
		        renderTo: document.body,
		        plugins: [rowEditing],
		        autoWidth: true,
		        autoHeight: true,
		        frame: true,
		        title: 'Transformers',
		        store: store,
		        multiSelect: true,
		        iconCls: 'icon-user',
		        columns: [{
		            text: 'No.',
		            width: 40,
		            sortable: true,
		            dataIndex: 'id',
		            field: {
		                xtype: 'numberfield',
		                value:4,
		                minValue: 3
		            }
		        }, {
		            text: 'Name',
		            width: 90,
		            sortable: true,
		            dataIndex: 'name',
		            field: {
		                xtype: 'textfield'
		            }
		        }, {
		            text: 'Type',
		            width: 100,
		            sortable: true,
		            dataIndex: 'type',
		            field: {
		                    xtype:"combobox",
		                    store:[ 'AUTOBOT', 'DECEPTICON'],
		                    editable:false
		            }
		        }, {
		            header: 'Strength',
		            width: 80,
		            sortable: true,
		            dataIndex: 'strength',
		            field: {
		                xtype: 'numberfield',
		                maxValue: 10,
		                minValue: 1
		            }
		        }, {
		            text: 'Intelligence',
		            width: 80,
		            sortable: true,
		            dataIndex: 'intelligence',
		            field: {
		            	xtype: 'numberfield',
		                maxValue: 10,
		                minValue: 1
		            }
		        }, {
		            text: 'Speed',
		            width: 80,
		            sortable: true,
		            dataIndex: 'speed',
		            field: {
		            	xtype: 'numberfield',
		                maxValue: 10,
		                minValue: 1
		            }
		        }, {
		            text: 'Endurance',
		            width: 80,
		            sortable: true,
		            dataIndex: 'endurance',
		            field: {
		            	xtype: 'numberfield',
		                maxValue: 10,
		                minValue: 1
		            }
		        }, {
		            text: 'Rank',
		            width: 80,
		            sortable: true,
		            dataIndex: 'rank',
		            field: {
		            	xtype: 'numberfield',
		                maxValue: 10,
		                minValue: 1
		            }
		        }, {
		            text: 'Courage',
		            width: 80,
		            sortable: true,
		            dataIndex: 'courage',
		            field: {
		            	xtype: 'numberfield',
		                maxValue: 10,
		                minValue: 1
		            }
		        }, {
		            text: 'Firepower',
		            width: 80,
		            sortable: true,
		            dataIndex: 'firepower',
		            field: {
		            	xtype: 'numberfield',
		                maxValue: 10,
		                minValue: 1
		            }
		        }, {
		            text: 'Skill',
		            width: 80,
		            sortable: true,
		            dataIndex: 'skill',
		            field: {
		            	xtype: 'numberfield',
		                maxValue: 10,
		                minValue: 1
		            }
		        }, {
		            text: 'Overall Rating',
		            width: 80,
		            sortable: true,
		            dataIndex: 'overallRating'
		        }],
		        dockedItems: [{
		            xtype: 'toolbar',
		            items: [{
		                text: 'Add',
		                //iconCls: 'icon-add',
		                handler: function(){
		                    // empty record
		                    store.insert(0, new Transformer());
		                    rowEditing.startEdit(0, 1);
		                }
		            }, '-', {
		                itemId: 'delete',
		                text: 'Delete',
		                //iconCls: 'icon-delete',
		                disabled: true,
		                handler: function(){
		                    var selection = grid.getView().getSelectionModel().getSelection()[0];
		                    if (selection) {
		                        store.remove(selection);
		                    }
		                }
		            }, '-', {
		                itemId: 'battle',
		                text: 'Battle',
		                //iconCls: 'icon-delete',
		                //disabled: true,
		                handler: function(){
		                    var selection = grid.getView().getSelectionModel().getSelection();
		                    var values = new Array();
		                    for (var i = 0; i<selection.length;i++){
		                    	values[i] = {id:selection[i].id};
		                    }
		                    Ext.Ajax.request({
		                        url: '/battleground/goBattle',
		                        method:'POST',
		                        jsonData: values,
				                headers:
				                {
				                    'Content-Type': 'application/json'
				                },
		                        success: function(response, opts) {
		                            var obj = Ext.decode(response.responseText);
		                            Ext.Msg.alert('Success', 'Number Of Battles' + obj.numberOfBattles 
		                            					+ '<br> Winning Team:' + obj.winningTeam
		                            					+ '<br> Members from Winning Team:' + obj.winningTeamMembers
		                            					+ '<br> Losing Team:' + obj.losingTeam
		                            					+ '<br> Members from Losing Team:' + obj.losingTeamMembers
		                            					+ '<br> Tie:' + obj.tie);
		                            //console.dir(obj);
		                        },

		                        failure: function(response, opts) {
		                        	Ext.Msg.alert('Alert', 'Failure');
		                            //console.log('server-side failure with status code ' + response.status);
		                        }
		                    });
		                }
		            }]
		        }]
		    });
		    grid.getSelectionModel().on('selectionchange', function(selModel, selections){
		        grid.down('#delete').setDisabled(selections.length === 0);
		    });
	});
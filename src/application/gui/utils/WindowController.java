package application.gui.utils;

import application.core.Main;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class WindowController implements EventHandler<MouseEvent> {

	private Stage stage;
	private Node minimizeButton, resizeButtonMin, resizeButtonMax, exitApplicationButton, resizeDragButton;
    private Cursor cursorEvent = Cursor.DEFAULT;
    private double border = 4, startX, startY, titlebarStartX, titlebarStartY;
    private Rectangle2D previousBounds, maxBounds;
    private boolean windowMaximized = false, resizeInAction = false;
    private double mouseEventX, mouseEventY, sceneWidth, sceneHeight;
    private Scene scene;

	public WindowController(Stage stage, Node titlebar, Node minimizeButton, Node resizeButtonMin, Node resizeButtonMax, Node exitApplicationButton, Node resizeDragButton) {
		this.stage = stage;
		scene = stage.getScene();
		this.minimizeButton = minimizeButton;
		this.resizeButtonMin = resizeButtonMin;
		this.resizeButtonMax = resizeButtonMax;
		this.resizeDragButton = resizeDragButton;
		this.exitApplicationButton = exitApplicationButton;

		maxBounds = Screen.getPrimary().getVisualBounds();

		if (resizeButtonMin != null) {
			this.resizeButtonMin.setOnMouseReleased(new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent e) { minimizeWindow(); }
			});
		}

		if (resizeButtonMax != null) {
			this.resizeButtonMax.setOnMouseReleased(new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent e) { maximizeWindow(); }
			});
		}

		if (minimizeButton != null) {
			this.minimizeButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent e) { stage.setIconified(true); }
			});
		}

		if (exitApplicationButton != null) {
			this.exitApplicationButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent e) { Main.closeApp(); }
			});
		}

		if (stage.isResizable()) {
	        stage.getScene().addEventHandler(MouseEvent.MOUSE_MOVED, this);
	        stage.getScene().addEventHandler(MouseEvent.MOUSE_PRESSED, this);
	        stage.getScene().addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
		}

        if (titlebar != null) addDraggableNode(titlebar);
	}


	public void maximizeWindow() {
		if (windowMaximized) {
			minimizeWindow();
			return;
		} else {
			previousBounds = new Rectangle2D(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight());
			stage.setX(maxBounds.getMinX());
			stage.setY(maxBounds.getMinY());
			stage.setWidth(maxBounds.getWidth());
			stage.setHeight(maxBounds.getHeight());
			if (resizeDragButton != null) resizeDragButton.setVisible(false);
			resizeButtonMax.setVisible(false);
			resizeButtonMin.setVisible(true);
			windowMaximized = true;
		}
	}

	public void minimizeWindow() {
		if (!windowMaximized) {
			minimizeWindow();
			return;
		} else {
			stage.setX(previousBounds.getMinX());
			stage.setY(previousBounds.getMinY());
			stage.setWidth(previousBounds.getWidth());
			stage.setHeight(previousBounds.getHeight());
			if (resizeDragButton != null) resizeDragButton.setVisible(true);
			resizeButtonMax.setVisible(true);
			resizeButtonMin.setVisible(false);
			windowMaximized = false;
		}

	}

	@Override
	public void handle(MouseEvent e) {
    	if (windowMaximized) return;
    	resizeInAction = false;
        EventType<? extends MouseEvent> mouseEventType = e.getEventType();

        mouseEventX = e.getSceneX();
        mouseEventY = e.getSceneY();
        sceneWidth = scene.getWidth();
        sceneHeight = scene.getHeight();

        if (MouseEvent.MOUSE_MOVED.equals(mouseEventType) == true) {
            if (mouseEventX < border && mouseEventY < border) {
                cursorEvent = Cursor.NW_RESIZE;
            } else if (mouseEventX < border && mouseEventY > sceneHeight - border) {
                cursorEvent = Cursor.SW_RESIZE;
            } else if (mouseEventX > sceneWidth - border && mouseEventY < border) {
                cursorEvent = Cursor.NE_RESIZE;
            } else if (mouseEventX > sceneWidth - border - 11 && mouseEventY > sceneHeight - border - 11) {
                cursorEvent = Cursor.SE_RESIZE;
            } else if (mouseEventX < border) {
                cursorEvent = Cursor.W_RESIZE;
            } else if (mouseEventX > sceneWidth - border) {
                cursorEvent = Cursor.E_RESIZE;
            } else if (mouseEventY < border) {
                cursorEvent = Cursor.N_RESIZE;
            } else if (mouseEventY > sceneHeight - border) {
                cursorEvent = Cursor.S_RESIZE;
            } else {
                cursorEvent = Cursor.DEFAULT;
            }
            scene.setCursor(cursorEvent);
        } else if (MouseEvent.MOUSE_PRESSED.equals(mouseEventType) == true) {
            startX = stage.getWidth() - mouseEventX;
            startY = stage.getHeight() - mouseEventY;
        } else if (MouseEvent.MOUSE_DRAGGED.equals(mouseEventType) == true) {
            if (Cursor.DEFAULT.equals(cursorEvent) == false) {
            	resizeInAction = true;
                if (Cursor.W_RESIZE.equals(cursorEvent) == false && Cursor.E_RESIZE.equals(cursorEvent) == false) {
                    double minHeight = stage.getMinHeight() > (border*2) ? stage.getMinHeight() : (border*2);
                    if (Cursor.NW_RESIZE.equals(cursorEvent) == true || Cursor.N_RESIZE.equals(cursorEvent) == true || Cursor.NE_RESIZE.equals(cursorEvent) == true) {
                        if (stage.getHeight() > minHeight || mouseEventY < 0) {
                            stage.setHeight(stage.getY() - e.getScreenY() + stage.getHeight());
                            stage.setY(e.getScreenY());
                        }
                    } else {
                        if (stage.getHeight() > minHeight || mouseEventY + startY - stage.getHeight() > 0) {
                            stage.setHeight(mouseEventY + startY);
                            if (stage.getY() + stage.getHeight() > maxBounds.getHeight()) stage.setHeight(maxBounds.getHeight() - stage.getY());
                        }
                    }
                }

                if (Cursor.N_RESIZE.equals(cursorEvent) == false && Cursor.S_RESIZE.equals(cursorEvent) == false) {
                    double minWidth = stage.getMinWidth() > (border*2) ? stage.getMinWidth() : (border*2);
                    if (Cursor.NW_RESIZE.equals(cursorEvent) == true || Cursor.W_RESIZE.equals(cursorEvent) == true || Cursor.SW_RESIZE.equals(cursorEvent) == true) {
                        if (stage.getWidth() > minWidth || mouseEventX < 0) {
                            stage.setWidth(stage.getX() - e.getScreenX() + stage.getWidth());
                            stage.setX(e.getScreenX());
                        }
                    } else {
                        if (stage.getWidth() > minWidth || mouseEventX + startX - stage.getWidth() > 0) {
                            stage.setWidth(mouseEventX + startX);
                            if (stage.getX() + stage.getWidth() > maxBounds.getWidth()) stage.setWidth(maxBounds.getWidth() - stage.getX());
                        }
                    }
                }
            }
        }
	}

	private void addDraggableNode(final Node node) {
		node.setOnMousePressed(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {
		            if (me.getButton() == MouseButton.PRIMARY) {
		            	titlebarStartX = me.getSceneX();
		            	titlebarStartY = me.getSceneY();
		            }
		        }
		    });
		    node.setOnMouseDragged(new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent me) {
		        	if (resizeInAction) return;
		            if (me.getButton() == MouseButton.PRIMARY && !windowMaximized) {
		            	double posX = me.getScreenX() - titlebarStartX, posY = me.getScreenY() - titlebarStartY;
		            	//if (posX < 5) posX = 0;
		            	//if (posX > maxBounds.getWidth() - node.getScene().getWidth()) posX = maxBounds.getWidth() - node.getScene().getWidth();
		            	if (posY < 5) posY = 0;
		            	if (posY > maxBounds.getHeight() - node.getScene().getHeight()) posY = maxBounds.getHeight() - node.getScene().getHeight();

			            node.getScene().getWindow().setX(posX);
			            node.getScene().getWindow().setY(posY);
		            }
		        }
		    });
		    node.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent me) {
					if(me.getButton().equals(MouseButton.PRIMARY)){
			            if(me.getClickCount() == 2) {
			            	scene.setCursor(Cursor.DEFAULT);
							if (!windowMaximized) {
								if (stage.isResizable()) maximizeWindow();
							} else {
								if (stage.isResizable()) minimizeWindow();
							}
			            }
			        }
				}
		    });
		}
}

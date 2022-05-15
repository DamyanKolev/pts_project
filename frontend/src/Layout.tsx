import { Outlet } from "react-router-dom";
import {
  ShellBar,
  Button,
  SideNavigation,
  SideNavigationItem,
  SideNavigationSubItem,
} from "@ui5/webcomponents-react";
import React from "react";

export default function PersistentDrawerLeft() {
  const [collapsed, setCollapsed] = React.useState<boolean>(true);

  const clickButton = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();

    if (collapsed == false) setCollapsed(true);
    else if (collapsed == true) setCollapsed(false);
  };

  const changePage = (url: string) => {
    window.location.href = url;
  };

  return (
    <div className="main-container">
      <ShellBar
        primaryTitle="Web Platform"
        startButton={<Button icon="menu" onClick={clickButton} />}
      ></ShellBar>

      <div className="content-container">
        <SideNavigation
          className="side-navigation"
          collapsed={collapsed}
        >
          <SideNavigationItem
            text="Home"
            icon="home"
            onClick={() => changePage("/")}
          />
          <SideNavigationItem text="Course"  icon="group">
            <SideNavigationSubItem
              text="ПТС"
              icon="course-book"
              onClick={() => changePage("api/pts")}
            />
            <SideNavigationSubItem
              text="ПМУ"
              icon="course-book"
              onClick={() => changePage("/api/pmu")}
            />
          </SideNavigationItem>
        </SideNavigation>

        <div className="page-content">
          <Outlet />
        </div>
      </div>
    </div>
  );
}

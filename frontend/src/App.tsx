import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./Layout";
import Home from "./pages/Home";
import Options from "./pages/Options";
import CAPage from "./pages/CAPage";
import DMPage from "./pages/DMPage";
import MCTPage from "./pages/MCTPage";
import FDPage from "./pages/FDPage";
import RASPage from "./pages/RASPage";

interface Path {
  url: string;
  component: React.ReactNode;
}

const getCoursePath = (arr1: string[], arr2: string[]) => {
  let result: Path[] = [];
  let i = 0;
  arr1.forEach((el) => {
    arr2.forEach((path) => {
      result.push({
        url: el.concat(path),
        component: components[i].component,
      });
      i++;
    });
    i = 0;
  });
  return result;
};

const components = [
  {
    component: <RASPage />,
  },
  {
    component: <FDPage />,
  },
  {
    component: <MCTPage />,
  },
  {
    component: <DMPage />,
  },
  {
    component: <CAPage />,
  },
];

function App() {
  let coursesPath = ["api/pts", "api/pmu"];
  let optionsPath = ["/RAS", "/FD", "/MCT", "/DM", "/CA"];

  return (
    <BrowserRouter>
      <Routes>
        <Route element={<Layout />}>
          <Route path="/" element={<Home />} />
          {coursesPath.map((path, index) => (
            <Route path={path} element={<Options />} key={index} />
          ))}
          {getCoursePath(coursesPath, optionsPath).map((path, index) => (
            <Route path={path.url} element={path.component} key={index} />
          ))}
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;

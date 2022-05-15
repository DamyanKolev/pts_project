import {
  Label,
  ResponsiveGridLayout,
  Table,
  TableCell,
  TableColumn,
  TableRow,
  Title,
} from "@ui5/webcomponents-react";
import React from "react";

interface MCTTableData {
  id: number;
  exercises: string;
}

interface MCTData {
  averageValue: number;
  median: number;
  mode: number;
  tableData: MCTTableData[];
}

export default function MCTPage() {
  const [data, setData] = React.useState<MCTData>();
  const [loading, setLoading] = React.useState<boolean>(true)

    const showLoading = () => (
        setLoading(true)
    )

    const hideLoading = () => (
        setLoading(false)
    )

  React.useEffect(() => {
    const fetchData = async () => {
      showLoading()
      await fetch("http://localhost:8080/api/options/MCT", {})
        .then((res) => res.json())
        .then((json) => {
          setData(json as any as MCTData);
        });
        hideLoading()
    };
    fetchData().catch(console.error);
  }, [loading]);

  return (
    <div className="page-container">
      <div className="page-bar">
        <Title level="H3">Мерки на централната тенденция</Title>
      </div>

      <div className="option-content">
        <ResponsiveGridLayout>
          <div
            style={{ gridColumn: "span 7", height: "35rem", overflow: "auto" }}
            className="responsiveGridLayoutItem"
          >
            <Table stickyColumnHeader
              columns={
                <>
                  <TableColumn popinText="ID" style={{ width: "15rem" }}>
                    <Label>ID</Label>
                  </TableColumn>
                  <TableColumn popinText="Качени упражнения">
                    <Label>Качени упражнения</Label>
                  </TableColumn>
                </>
              }
            >
              {data?.tableData.map((row, index) => (
                <TableRow key={index}>
                  <TableCell>
                    <Label>{row.id}</Label>
                  </TableCell>
                  <TableCell>
                    <Label>{row.exercises}</Label>
                  </TableCell>
                </TableRow>
              ))}
            </Table>
          </div>

          <div
            style={{
              gridColumn: "span 3",
              display: "flex",
              flexDirection: "column",
              gap: "0.3rem",
            }}
            className="responsiveGridLayoutItem"
          >
            <div className="page-data-container">
              <Title level="H5">Средна стойност</Title>
              <Title level="H5">{data?.averageValue.toFixed(2)}</Title>
            </div>
            <div className="page-data-container">
              <Title level="H5">Медиана</Title>
              <Title level="H5">{data?.median}</Title>
            </div>
            <div className="page-data-container">
              <Title level="H5">Мода</Title>
              <Title level="H5">{data?.mode}</Title>
            </div>
          </div>
        </ResponsiveGridLayout>
      </div>
    </div>
  );
}

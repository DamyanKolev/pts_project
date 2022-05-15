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

interface DMTableData {
  id: number;
  exercises: string;
}

interface DMData {
  deviation: number;
  dispersion: number;
  scope: number;
  tableData: DMTableData[];
}

export default function DMPage() {
  const [data, setData] = React.useState<DMData>();
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
      await fetch("http://localhost:8080/api/options/DM", {

      })
        .then((res) => res.json())
        .then((json) => {
      console.log(json)
          setData(json as any as DMData);
        });
        hideLoading()
    };
    fetchData().catch(console.error);
  },[loading]);

  return (
    <div className="page-container">
      <div className="page-bar">
        <Title level="H3">Мерки на разсейването</Title>
      </div>

      <div className="option-content">
        <ResponsiveGridLayout>
          <div
            style={{ gridColumn: "span 7", height: '35rem',
            overflow: 'auto'}}
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
              <Title level="H5">Стандартно отклонение</Title>
              <Title level="H5">{data?.deviation.toFixed(2)}</Title>
            </div>
            <div className="page-data-container">
              <Title level="H5">Дисперсия</Title>
              <Title level="H5">{data?.dispersion.toFixed(2)}</Title>
            </div>
            <div className="page-data-container">
              <Title level="H5">Размах</Title>
              <Title level="H5">{data?.scope.toFixed(2)}</Title>
            </div>
          </div>
        </ResponsiveGridLayout>
      </div>
    </div>
  );
}

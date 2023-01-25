import * as React from "react";
import Box from "@mui/material/Box";
import BottomNavigation from "@mui/material/BottomNavigation";
import BottomNavigationAction from "@mui/material/BottomNavigationAction";
import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import CalendarMonthOutlinedIcon from "@mui/icons-material/CalendarMonthOutlined";
import PhotoOutlinedIcon from "@mui/icons-material/PhotoOutlined";
import Diversity1OutlinedIcon from "@mui/icons-material/Diversity1Outlined";
import PortraitOutlinedIcon from "@mui/icons-material/PortraitOutlined";

export default function SimpleBottomNavigation() {
  const [value, setValue] = React.useState(0);

  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        position: "fixed",
        bottom: 0,
        left: 0,
        right: 0,
      }}
    >
      <BottomNavigation
        showLabels
        value={value}
        onChange={(event, newValue) => {
          setValue(newValue);
        }}
      >
        <BottomNavigationAction label="Home" icon={<HomeOutlinedIcon />} />
        <BottomNavigationAction
          label="Calendar"
          icon={<CalendarMonthOutlinedIcon />}
        />
        <BottomNavigationAction
          label="PhotoBook"
          icon={<PhotoOutlinedIcon />}
        />
        <BottomNavigationAction
          label="가치가자"
          icon={<Diversity1OutlinedIcon />}
        />

        <BottomNavigationAction
          label="가치한장"
          icon={<PortraitOutlinedIcon />}
        />
      </BottomNavigation>
    </Box>
  );
}

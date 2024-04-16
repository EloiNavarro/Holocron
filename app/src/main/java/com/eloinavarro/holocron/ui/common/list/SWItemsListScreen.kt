package com.eloinavarro.holocron.ui.common.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.eloinavarro.holocron.R
import com.eloinavarro.holocron.domain.SWItem
import com.eloinavarro.holocron.ui.common.Paginated
import com.eloinavarro.holocron.ui.common.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T: SWItem> SWItemsListScreen(
    uiState: ListUiState<T>,
    viewModel: Paginated,
    onItemClick: (T) -> Unit
) {

    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.app_name)) },
                    navigationIcon = {}
                )
            }
        ) { padding ->
            LazyVerticalGrid(
                columns = GridCells.Adaptive(180.dp),
                contentPadding = PaddingValues(4.dp),
                modifier = Modifier.padding(padding)
            ) {
                items(uiState.items.size) { index ->
                    if (index >= uiState.items.size - 1
                        && !uiState.endReached
                        && !uiState.loading
                    ) {
                        viewModel.loadNextPage()
                    }
                    SWListItem(
                        swItem = uiState.items[index],
                        modifier = Modifier.clickable {
                            onItemClick(uiState.items[index])
                        }
                    )
                }
                item (
                    span = {
                        GridItemSpan(2)
                    }
                ) {
                    if (uiState.loading) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}